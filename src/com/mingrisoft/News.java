package com.mingrisoft;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class News {
	DBConnection DBConn = new DBConnection();                      // 引入连接数据库方法类
	Function Fun = new Function();                                  // 引入数据处理方法类
	//新闻列表查询方法
	public String ListNewsFront(String sPage, String strPage) {
		try {                                                        // 用于获取系统运行异常信息
			Connection Conn = DBConn.getConn();                  // 建立数据库连接
			Statement stmt = Conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);            // 创建数据查询
			ResultSet rs = null;                                  // 定义结果集			
			// 定义本方法返回字符串数据
			StringBuffer sb = new StringBuffer();
			int i;                                                 // 定义数字型变量
			int intPage = 1;                                      // 定义数字型变量并赋值1
			int intPageSize = 5;                                 // 定义数字型变量并赋值5
			// 创建sql语句查询News表全部信息
			String sSql = "select * from news";
			// 通过执行sql语句得到查询结果
			rs = stmt.executeQuery(sSql);	
			if (!rs.next()) {                                     // 判定当查询结果为空
				// 返回属性添加字符串数据用于页面显示
				sb.append("<tr height=\"25\" bgcolor=\"#d6dff7\"  class=\"info1\">");
	            sb.append("<td colspan=\\\"5\\\">\\r\\n\")");
				// 返回属性添加字符串数据用于页面显示
				sb.append("<div align=\"center\"><b>没有记录!</b></div></td></tr>\r\n");
			} else {                                               // 判定当查询结果是否为空
				// 将传入参数strPage进行数据格式转换
				intPage = Fun.StrToInt(strPage);
				// 将传入参数sPage进行数据处理
				sPage = Fun.CheckReplace(sPage);
				if (intPage == 0) {                              // 判定intPage为0
					intPage = 1;                                 // 参数intPage赋值为1
				}
				int NewsID2 = rs.getInt("NewsID");
				String NewsTitle2 = rs.getString("NewsTitle");
				// 定义数字型变量并赋值News表里的NewsTime属性
				String NewsTime2 = rs.getString("NewsTime");
				// 定义数字型变量并赋值News表里的AdminName属性
				String AdminName2 = rs.getString("AdminName");
				// 计算当前页面显示新闻条数
				rs.absolute((intPage - 1) * intPageSize + 1);
				i = 0;                                            // 参数i赋值为0
				// i属性小于页面显示条数并且查询结果集不为空，进行循环方法
				while (i < intPageSize && !rs.isAfterLast()) {
					// 定义数字型变量并赋值News表里的NewsID属性
					int NewsID = rs.getInt("NewsID");
					// 定义数字型变量并赋值News表里的NewsTitle属性
					String NewsTitle = rs.getString("NewsTitle");
					// 定义数字型变量并赋值News表里的NewsTime属性
					String NewsTime = rs.getString("NewsTime");
					// 定义数字型变量并赋值News表里的AdminName属性
					String AdminName = rs.getString("AdminName");
					// 返回属性添加字符串数据用于页面显示<tr>表示换行
					sb.append("<tr>");
					// 返回属性添加字符串数据用于页面显示新闻标题
					sb.append("<td>" + NewsTitle + "</td>");
					// 返回属性添加字符串数据用于页面显示用户名
					sb.append("<td >" + AdminName + "</td>");
					// 返回属性添加字符串数据用于页面显示新闻时间
					sb.append("<td >" + NewsTime + "</td>");
					// 返回属性添加字符串数据用于页面显示详情按钮
			         sb.append("<td ><a style=\"color:#3F862E\" target=\"_blank\" href=\"newsFrontDetail.jsp?newsId="
							+ NewsID + "\">详情</a></td></tr>");
					rs.next();                                  // 判定是否存在下一条信息
					i++;                                        // i属性数值自增1
				}
				// 拼写字符串数据用于列表最下方的分页方法
				sb.append(Fun.PageFront(sPage, rs, intPage, intPageSize));
			}
			rs.close();                                          // 关闭结果集
			stmt.close();                                       // 关闭查询
			Conn.close();                                       // 关闭数据连接
			return sb.toString();                              // 返回字符串数据
		} catch (Exception e) {                                // 得到系统运行异常
			return "No";                                       // 如果系统异常方法返回字符“No”
		}
	}

}
