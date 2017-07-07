import os
import re
from TwitterAPI import TwitterAPI, TwitterOAuth, TwitterRestPager
import datetime
import sys

def dateCheck():
    dat=('{:%Y-%m-%d}'.format(datetime.datetime.now()))
    if os.path.isfile('TimStmp.txt'):
        with open('TimStmp.txt') as f:
            content = f.readlines()
        if dat!=content[0]:
            os.remove('TimStmp.txt')
            timestamp=open('TimStmp.txt', 'w')
            timestamp.write(dat)
            timestamp.close()
            return True
        else:
            return False

    else:
        timestamp=open('TimStmp.txt', 'w')
        timestamp.write(dat)
        timestamp.close()
        return True
def getTweets():

    o = TwitterOAuth.read_file('credentials.txt')
    twitter = TwitterAPI(o.consumer_key,
                     o.consumer_secret,
                     o.access_token_key,
                     o.access_token_secret)
    screen_name = 'v_explore'
    return [tweet for tweet in twitter.request('statuses/user_timeline',
                                                {'screen_name': screen_name,
                                                 'count': 200})]

def fileWrite(timeline):
    try:
        os.remove('TravelLists.txt')
    except:
        pass
    try:
        os.remove('TravelPlans.txt')
    except:
        pass
    try:
        os.remove('TravelTips.txt')
    except:
        pass
    TravelLists = open('TravelLists.txt', 'ab')
    TravelPlans = open('TravelPlans.txt', 'ab')
    TravelTips = open('TravelTips.txt', 'ab')
    for m in timeline:
        if m['text'].find('#TravelLists')!=-1:
            TravelLists.write((m['text'].replace('\n',' ').replace('#TravelLists','')+'\n').encode('ascii','ignore'))
        if m['text'].find('#TravelPlans')!=-1:
            TravelPlans.write((m['text'].replace('\n',' ').replace('#TravelPlans','')+'\n').encode('ascii','ignore'))
        if m['text'].find('#TravelTips')!=-1:
            TravelTips.write((m['text'].replace('\n',' ').replace('#TravelTips','')+'\n').encode('ascii','ignore'))
    TravelLists.close()
    TravelPlans.close()
    TravelTips.close()

#getTweets()
#fileWrite()
if os.path.isfile('credentials.txt'):
    pass
else:
    print ("Twitter credentials missing")
    sys.exit()
if dateCheck():

    fileWrite(getTweets())
else:
    pass
