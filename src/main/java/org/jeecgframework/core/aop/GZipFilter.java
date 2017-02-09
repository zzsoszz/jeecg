/*    */ package org.jeecgframework.core.aop;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.log4j.Logger;
/*    */ import org.springframework.util.FileCopyUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GZipFilter
/*    */   implements Filter
/*    */ {
/* 27 */   private static final Logger logger = Logger.getLogger(GZipFilter.class);
/*    */   private static final String STATIC_TEMPLATE_SOURCE = "online/template";
/*    */   private static final String STATIC_TEMPLATE_SOURCE_2 = "clzcontext/template";
/*    */   private static final String STATIC_TEMPLATE_SOURCE_3 = "/content/";
/*    */   private static final String STATIC_TEMPLATE_SOURCE_4 = "/plug-in-ui/";
/*    */   private static final String NO_FILTER = ".do";
/*    */   private static final String DIAN = ".";
/*    */   
/*    */   public void destroy() {}
/*    */   
/*    */   public void init(FilterConfig arg0)
/*    */     throws ServletException
/*    */   {}
/*    */   
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
/*    */   {
/* 43 */     HttpServletResponse resp = (HttpServletResponse)response;
/* 44 */     HttpServletRequest req = (HttpServletRequest)request;
/* 45 */     String url = req.getRequestURI();
/* 46 */     String path = req.getContextPath();
/* 47 */     String basePath = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + path;
/*    */     
/* 49 */     if (((req.getRequestURI().indexOf("online/template") != -1) || (req.getRequestURI().indexOf("clzcontext/template") != -1) || (req.getRequestURI().indexOf("/content/") != -1) || (req.getRequestURI().indexOf("/plug-in-ui/") != -1)) && (req.getRequestURI().indexOf(".") != -1) && (req.getRequestURI().indexOf(".do") == -1))
/*    */     {
/*    */ 
/*    */ 
/* 53 */       if (url.startsWith(req.getContextPath())) {
/* 54 */         url = url.replaceFirst(req.getContextPath(), "");
/*    */       }
/* 56 */       response.reset();
/* 57 */       String s = ResMime.get(url.substring(url.lastIndexOf(".")).replace(".", ""));
/* 58 */       if (s != null) response.setContentType(s);
/* 59 */       OutputStream os = null;
/* 60 */       InputStream is = null;
/*    */       try
/*    */       {
/* 63 */         is = getClass().getResourceAsStream(url);
/* 64 */         if (is != null) {
/* 65 */           os = response.getOutputStream();
/* 66 */           FileCopyUtils.copy(is, os);
/*    */         }
/*    */         else {
/* 69 */           chain.doFilter(request, response);
/*    */         }
/*    */       } catch (IOException e) {
/* 72 */         e.printStackTrace();
/*    */       } finally {
/* 74 */         if (os != null) {
/*    */           try {
/* 76 */             os.close();
/*    */           }
/*    */           catch (IOException e) {}
/*    */         }
/*    */         
/*    */ 
/* 82 */         if (is != null) {
/*    */           try {
/* 84 */             is.close();
/*    */           }
/*    */           catch (IOException e) {}
/*    */         }
/*    */       }
/*    */     }
/*    */     
/*    */ 
/* 92 */     chain.doFilter(request, response);
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\.m2\repository\org\jeecgframework\codegenerate\3.6.1-SNAPSHOT\codegenerate-3.6.1-20160919.020334-2.jar!\org\jeecgframework\core\aop\GZipFilter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */