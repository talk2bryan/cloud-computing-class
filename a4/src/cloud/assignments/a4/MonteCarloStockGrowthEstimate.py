import random
import numpy
import time
import math

from operator import add

STRIKE_PRICE = 30 # k 
ASSET_PRICE = 70 # s == asset
T = 1 # 1 year, 12 months == 12 steps (N)
STEPS = 12 # N
RATE = 0.12 # r == 12%
SIGMA = 0.4
THETA = 0

####################################
# Computing the constants*
####################################

dt = T/float(STEPS)
nudt = ( RATE - THETA - (pow(SIGMA, 2)/2) ) * dt
sigsdt = SIGMA * pow(dt, 0.5)
lnS = math.log(ASSET_PRICE)
discount_factor = math.exp(-RATE * T) 


def call_payoff(asset_price,STRIKE_PRICE):
    return max(0.0,asset_price - STRIKE_PRICE)

def grow(seed):
	random.seed(seed)
	portfolio_value = ASSET_PRICE
	lnSt = math.log(ASSET_PRICE) # lnS
	for x in range(STEPS):
		lnSt += nudt + sigsdt * numpy.random.normal()
	asset_price_ST = math.exp(lnSt)
	portfolio_value += call_payoff(asset_price_ST, STRIKE_PRICE)
	return portfolio_value

seeds = sc.parallelize([time.time() + i for i in xrange(10000)])
results = seeds.map(grow)
sum = results.reduce(add)
price = sum/10000.
print price

# '${:,.2f}'.format(price)