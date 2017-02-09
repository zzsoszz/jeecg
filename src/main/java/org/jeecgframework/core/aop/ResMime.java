/*    */ package org.jeecgframework.core.aop;
/*    */ 
/*    */ import java.util.Map;
/*    */ 
/*    */ public class ResMime
/*    */ {
/*  7 */   private static final Map<String, String> map = new java.util.HashMap();
/*    */   
/*  9 */   static { map.put("abs", "audio/x-mpeg");
/* 10 */     map.put("css", "text/css");
/* 11 */     map.put("js", "text/javascript");
/* 12 */     map.put("png", "image/png");
/* 13 */     map.put("jpg", "image/jpeg");
/* 14 */     map.put("swf", "application/x-shockwave-flash");
/* 15 */     map.put("xls", "application/vnd.ms-excel");
/* 16 */     map.put("gif", "image/gif");
/*    */   }
/*    */   
/* 19 */   public static String get(String s) { return (String)map.get(s); }
/*    */ }


/* Location:              C:\Users\Administrator\.m2\repository\org\jeecgframework\codegenerate\3.6.1-SNAPSHOT\codegenerate-3.6.1-20160919.020334-2.jar!\org\jeecgframework\core\aop\ResMime.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */