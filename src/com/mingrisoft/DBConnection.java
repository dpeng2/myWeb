package com.mingrisoft;			//指定类所在的包

import java.sql.*;						//导入数据库操作的类
import java.util.*;
import java.io.*;

import com.mingrisoft.Function;

public class DBConnection					
{

    private String FileName;			//配置文件名
    private int DBType;					//数据库类型
    private Connection conn;			//连接对象
    private String MySqlDriver;			//MYSQL Server驱动程序
    private String MySqlURL; 			//MYSQL Server连接字符串
    private String MyUser;				//MYSQL Server用户名
    private String MyPassword;			//MYSQL Server密码
        
   
    public DBConnection()
    {
    	conn = null;
    }

	public  Connection getConn()
	{

		DBType= new Function().StrToInt(getPara("DBType"));
	
		switch(DBType)
		{
			case 1:return(getConnToMySql());
			default:return null;
		}	
	}
	
	
	public String getPara(String ParaName) 
	{
		FileName="../DBConfig.property";
		Properties prop= new Properties();
		try
		{
			InputStream is=getClass().getResourceAsStream(FileName);
			prop.load(is);
			if(is!=null) is.close();
		}
		catch(Exception e) {
			return "Error!";
		}
		return prop.getProperty(ParaName);
	}
	
	
    public Connection getConnToMySql()
    {
    	Connection conn3 = null;
    	try{
	 		MySqlDriver = getPara("MySQLDriver");	
	    	MySqlURL = getPara("MySQLURL");
	    	MyUser = getPara("MyUser");
	    	MyPassword = getPara("MyPassword");
	    	Class.forName(MySqlDriver).newInstance();
	    	conn = DriverManager.getConnection(MySqlURL,MyUser,MyPassword);
	    	//conn3 = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/webdb?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8", "root", "root");
	    	}catch(Exception e){
	    		e.printStackTrace();
		    	System.err.println(e.getMessage());
	    	}
	    return conn;
    }       
    
}
