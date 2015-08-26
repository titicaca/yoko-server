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
	base64string = base64.encodestring("%s:%s" % (username, password))[:-1] 
	
	authheader =  "Basic %s" % base64string
	req = urllib2.Request(url, values)       # 生成页面请求的完整数据
	req.add_header("Authorization", authheader)
	#req.add_header("Accept","application/json")
	req.get_method=lambda:posttype
	response = urllib2.urlopen(req)       # 发送页面请求
	responsejson=json.loads(response.read())
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
	resp = http_nosecurity(url,"","PUT")

	print "Post organization"
	url=host+"/signup/organization"
	values ={"name":"LIGANG","role_mobile":"1_1233321","password":"ooo"}
	resp = http_nosecurity(url,values,"POST")

	print "Post organization"
	url=host+"/signup/organization"
	values ={"name":"有空","role_mobile":"1_1233322","password":"ooo"}
	resp = http_nosecurity(url,values,"POST")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=ooo&username=1_1233321&grant_type=password"
	token = http_gettoken(url,values,"POST")

	# print "Put organization"
	# url=host+"/organization/orginfo"
	# values ={"name":"yiqiwan","card":"111111111","mobile":"1233321"}
	# resp = http_security(url,values,"PUT")

	print "Post activity"
	url=host+"/organization/myactivity/host"
	values ={"name":"trip","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Post activity"
	url=host+"/organization/myactivity/host"
	values ={"name":"trip2","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Post activity"
	url=host+"/organization/myactivity/host"
	values ={"name":"trip3","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"有  空","role_mobile":"0_13512147293","password":"qq1111"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"Michael","role_mobile":"0_123457","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"莎","role_mobile":"0_123457000","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"hehehe","role_mobile":"0_123","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"hahaha","role_mobile":"0_1234","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"yokoyoko","role_mobile":"0_12345","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"yokoyokoo","role_mobile":"0_123455","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"yokoyokoooo","role_mobile":"0_1234599","password":"helloworld"}
	resp = http_nosecurity(url,values,"POST")

	print "Put User"
	url= host+"/signup/user"
	values ={"role_mobile":"0_123457","password":"1111"}
	resp = http_nosecurity(url,values,"PUT")

	print "Post User"
	url= host+"/signup/user"
	values ={"name":"ssss","role_mobile":"0_13700001111","password":"qq1111"}
	resp = http_nosecurity(url,values,"POST")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=1111&username=0_123457&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "Add push info"
	url=host+"/pushinfo"
	values={"userid":231241413232,"channelid":3720523957414978342,"devicetype":3}
	resp = http_security(url,values,"POST")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=qq1111&username=0_13512147293&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "Add push info"
	url=host+"/pushinfo"
	values={"userid":231241413232,"channelid":4418408219469130323,"devicetype":3}
	resp = http_security(url,values,"POST")

	print "Get User"
	url=host+"/user/userinfo"
	resp = http_security(url,"","GET")

	print "Watch organization"
	url=host+"/user/myactivity/watch/1"
	resp = http_security(url,"","POST")

	print "Post schedule"
	url=host+"/user/myschedule"
	values ={"name":"trip","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Post schedule"
	url=host+"/user/myschedule"
	values ={"name":"trip2","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Post schedule"
	url=host+"/user/myschedule"
	values ={"name":"trip3","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "Get schedule"
	url=host+"/user/myschedule/schedules"
	resp = http_security(url,"","GET")

	print "Post appointment"
	url=host+"/user/myappointment/host"
	values ={"name":"trip","location":"Shanghai"}
	resp = http_security(url,values,"POST")

	print "GET appointment"
	url=host+"/user/myappointment/host/appointments"
	resp = http_security(url,values,"GET")

	print "Appointment invite user"
	url=host+"/user/myappointment/enroll/invite"
	values ={"user_id":2,"appointment_id":1}
	resp = http_security(url,values,"POST")

	print "Invite response ok"
	url=host+"/user/myappointment/enroll/response"
	values ={"user_id":2,"appointment_id":1,"status":1}
	resp = http_security(url,values,"PUT")

	
	print "User collect activity"
	url=host+"/user/myactivity/collect/1"
	resp = http_security(url,values,"POST")

	print "User collect activity"
	url=host+"/user/myactivity/collect/2"
	resp = http_security(url,values,"POST")

	print "User collect activity"
	url=host+"/user/myactivity/collect/2"
	resp = http_security(url,values,"POST")

	print "User decollect activity"
	url=host+"/user/myactivity/collect/1"
	resp = http_security(url,values,"DELETE")

	print "User enroll activity"
	url=host+"/user/myactivity/enroll/1"
	resp = http_security(url,values,"POST")

	print "User enroll activity"
	url=host+"/user/myactivity/enroll/2"
	resp = http_security(url,values,"POST")

	print "User enroll activity"
	url=host+"/user/myactivity/enroll/3"
	resp = http_security(url,values,"POST")

	print "User anti-enroll activity"
	url=host+"/user/myactivity/enroll/2"
	resp = http_security(url,values,"DELETE")

	print "Get collect activity by user"
	url=host+"/user/myactivity/collect/activities"
	resp = http_security(url,"","GET")

	print "Get enroll activity by user"
	url=host+"/user/myactivity/enroll/activities"
	resp = http_security(url,"","GET")

	print "Request friend"
	url=host+"/user/myfriend/request/2"
	values={"msg":"kuma你好快加我"}
	resp = http_security(url,values,"POST")

	print "Request friend"
	url=host+"/user/myfriend/request/3"
	values={"msg":"helloaaaaaa"}
	resp = http_security(url,values,"POST")

	print "Request friend"
	url=host+"/user/myfriend/request/4"
	values={"msg":"hello1312321"}
	resp = http_security(url,values,"POST")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=1111&username=0_123457&grant_type=password"
	token = http_gettoken(url,values,"POST")


	print "response friend"
	url=host+"/user/myfriend/response/1"
	resp = http_security(url,"","PUT")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=helloworld&username=0_123457000&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "response friend"
	url=host+"/user/myfriend/response/1"
	resp = http_security(url,"","PUT")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=helloworld&username=0_123&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "response friend"
	url=host+"/user/myfriend/response/1"
	resp = http_security(url,"","PUT")


	print "Get token"
	url=host+"/oauth/token"
	values ="password=qq1111&username=0_13512147293&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "Post Tag"
	url=host+"/user/mytag"
	values ={"tagname":"friend"}
	resp = http_security(url,values,"POST")

	print "Add tag friend"
	url=host+"/user/mytag/1/2"
	resp = http_security(url,"","PUT")

	print "Add tag friend"
	url=host+"/user/mytag/1/3"
	resp = http_security(url,"","PUT")

	print "Post Tag"
	url=host+"/user/mytag"
	values ={"tagname":"friendnew"}
	resp = http_security(url,values,"POST")

	print "Post Tag"
	url=host+"/user/mytag"
	values ={"tagname":"friendnewnewnew"}
	resp = http_security(url,values,"POST")

	print "Add tag friend"
	url=host+"/user/mytag/2/2"
	resp = http_security(url,"","PUT")

	print "Add tag friend"
	url=host+"/user/mytag/2/4"
	resp = http_security(url,"","PUT")

	print "get tag friends"
	url=host+"/user/mytag/1/friends"
	resp = http_security(url,"","GET")

	print "get friend tags"
	url=host+"/user/myfriend/2/tags"
	resp = http_security(url,"","GET")

	print "Get all friend"
	url=host+"/user/myfriend/friends"
	resp = http_security(url,"","GET")

	print "Get all friend info"
	url=host+"/user/myfriend/allinfo"
	resp = http_security(url,"","GET")

	print "Get User"
	url=host+"/user/userinfo"
	resp = http_security(url,"","GET")

	print "Search friend"
	url=host+"/user/myfriend/search"
	values ={"mobile":"123457"}
	resp = http_security(url,values,"POST")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=1111&username=0_123457&grant_type=password"
	token = http_gettoken(url,values,"POST")

	print "Get friend 1"
	url=host+"/user/myfriend/1/info"
	resp = http_security(url,"","GET")

	print "Get token"
	url=host+"/oauth/token"
	values ="password=qq1111&username=0_13512147293&grant_type=password"
	token = http_gettoken(url,values,"POST")

	

	print "Post Tag and friendlist"
	url=host+"/user/mytag/friendlist"
	values = {"tagname":"friendfxxxxxxx","friendlist":[{"id":2},{"id":3},{"id":4}]} 
	resp = http_security(url,values,"POST")

	# print "Post Tag and friendlist"
	# url=host+"/user/mytag/4/friendlist"
	# values = {"tagname":"hehehehe","friendlist":[{"id":2},{"id":3}]} 
	# resp = http_security(url,values,"PUT")

	# print "Delete Tag"
	# url=host+"/user/mytag/4"
	# resp = http_security(url,"","DELETE")

	print "Get Page activity"
	url=host+"/user/myactivity/activities/page/0/4"
	resp = http_security(url,"","GET")







