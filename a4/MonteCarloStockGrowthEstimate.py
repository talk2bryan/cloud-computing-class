import random
import numpy
import time
import math
from pyspark import SparkFiles

from operator import add


'''
To run:
    In pyspark: copy and paste this code
'''

STRIKE_PRICE = 105  # K
ASSET_PRICE = 100  # S
T = 1  # 1 year
STEPS = 100  # N 
RATE = 0.05  # R == 12%
SIGMA = 4.5
THETA = 0
# m steps, n num simulations

####################################
# Computing the constants*
####################################

dt = T/float(STEPS)
nudt = (RATE - THETA - (pow(SIGMA, 2)/2)) * dt
sigsdt = SIGMA * pow(dt, 0.5)
lnS = math.log(ASSET_PRICE)
discount_factor = math.exp(-RATE * T)


path = os.path.join("./", "ints.txt")
sc.addFile(path)


def call_payoff(asset_price, STRIKE_PRICE):
    return max(0.0, asset_price - STRIKE_PRICE)

def grow(seed):
    numpy.random.seed(int(seed))
    portfolio_value = ASSET_PRICE
    lnSt = lnS
    for x in range(STEPS):
        lnSt += nudt + sigsdt * numpy.random.normal()
    asset_price_ST = math.exp(lnSt)
    portfolio_value += call_payoff(asset_price_ST, STRIKE_PRICE)
    
    return portfolio_value

###########################################
# This method helps simulate getting the 
# values in the intermediate steps as it
# could not be accessed in grow() because
# they were being run on worker nodes. You
# said it was okay to do this.
###########################################
def mockIntermediateValues(seed, n):
    numpy.random.seed(int(seed))
    portfolio_value = ASSET_PRICE
    lnSt = lnS
    f = open("ints_{num}.txt".format(num=n), "a+")
    for x in range(STEPS):
        lnSt += nudt + sigsdt * numpy.random.normal()
        val = portfolio_value+lnSt
        f.write("%0.2f\n" % val)
        
    asset_price_ST = math.exp(lnSt)
    portfolio_value += call_payoff(asset_price_ST, STRIKE_PRICE)
    
    f.close()

seeds = sc.parallelize([time.time() + i for i in xrange(100000)])
results = seeds.map(grow)

sum = results.reduce(add)
price = sum/100000.

# print option value
"${:,.2f}".format(price)


# Run 5 simulations for spreadsheet report
for x in xrange(1,6):
    mockIntermediateValues(time.time(), x)
