#!/usr/bin/env python
# coding:utf-8
# File http_post.py

import urllib2
import json
import base64     

token=""

def http_gettoken(url,values,posttype):

	#values = json.dumps(values)
	username="android-yoko"
	password="123456"
	base64string1 = base64.encodestring("%s:%s" % (username, password))
	print "base64string1="+base64string1
	base64string = base64.encodestring("%s:%s" % (username, password))[:-1] 
	print "base64string="+base64string
	authheader =  "Basic %s" % base64string
	print authheader
	req = urllib2.Request(url, values)       # 生成页面请求的完整数据
	req.add_header("Authorization", authheader)
	#req.add_header("Accept","application/json")
	req.get_method=lambda:posttype
	response = urllib2.urlopen(req)       # 发送页面请求
	responsejson=json.loads(response.read())
	print responsejson
	token = responsejson["access_token"];
	print token
	print ""
	return token

def http_nosecurity(url,values,posttype):
        jdata = json.dumps(values)             # 对数据进行JSON格式化编码
        req = urllib2.Request(url, jdata)       # 生成页面请求的完整数据
        req.add_header("Content-Type","application/json")
        req.get_method=lambda:posttype
        response = urllib2.urlopen(req)       # 发送页面请求
        print (response.read())                    # 获取服务器返回的页面信息
        print ""

def http_security(url,values,posttype):
	authheader =  "Bearer %s" % token
	jdata = json.dumps(values)             # 对数据进行JSON格式化编码
        req = urllib2.Request(url, jdata)       # 生成页面请求的完整数据
        req.add_header("Authorization", authheader)
	print authheader
	req.add_header("Accept","application/json")
        req.add_header("Content-Type","application/json")
        req.get_method=lambda:posttype
        response = urllib2.urlopen(req)       # 发送页面请求
        print (response.read())                    # 获取服务器返回的页面信息
        print ""
		
if __name__ == "__main__":
	#host="http://10.211.55.5:8080"
	#host="http://127.0.0.1:8080"
	#hostssl="https://10.1.1.216:12345"

	#host="http://139.196.16.75:8080"
	host = "http://127.0.0.1:8080"

	print "Get Homepage"
	url= host+"/home"
	resp = http_nosecurity(url,"","GET")	

	print "Get activity"
	activity_id="1"
	url=host+"/public/activity/"+activity_id;
	resp=http_nosecurity(url,"","GET")
	
	print "Post organization"
	url=host+"/signup/organization"
	values ={"name":"严淮","role_mobile":"1_15121116296","password":"ooo"}
	resp = http_nosecurity(url,values,"POST")
	
	print "Get token"
	url=host+"/oauth/token"
	values ="password=ooo&username=1_1233321&grant_type=password"
	token = http_gettoken(url,values,"POST")
	
	print "Post activity"
	url=host+"/organization/myactivity/host"
	values ={"name":"trip23","location":"Shanghai"}
	resp = http_security(url,values,"POST")


