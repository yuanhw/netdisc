package netdisc.util;

import static java.lang.System.out;

import java.util.*;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import netdisc.entity.*;
import netdisc.service.*;
import netdisc.service.factory.*;
import netdisc.web.ALSendCode;
import netdisc.web.SendCode;

public class Client {

	public static void main(String[] args) throws Exception {
		String str=ALSendCode.sendCode("13588746259");
		out.println(str);
	}

}
