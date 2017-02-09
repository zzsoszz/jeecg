/*    */ package org.jeecgframework.core.aop;
/*    */ 
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintWriter;
/*    */ import javax.servlet.ServletOutputStream;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ 
/*    */ public class Wrapper extends javax.servlet.http.HttpServletResponseWrapper
/*    */ {
/*    */   public static final int OT_NONE = 0;
/*    */   public static final int OT_WRITER = 1;
/*    */   public static final int OT_STREAM = 2;
/* 14 */   private int outputType = 0;
/* 15 */   private ServletOutputStream output = null;
/* 16 */   private PrintWriter writer = null;
/* 17 */   private ByteArrayOutputStream buffer = null;
/*    */   
/*    */   public Wrapper(HttpServletResponse resp) throws IOException {
/* 20 */     super(resp);
/* 21 */     this.buffer = new ByteArrayOutputStream();
/*    */   }
/*    */   
/*    */   public PrintWriter getWriter() throws IOException {
/* 25 */     if (this.outputType == 2)
/* 26 */       throw new IllegalStateException();
/* 27 */     if (this.outputType == 1) {
/* 28 */       return this.writer;
/*    */     }
/* 30 */     this.outputType = 1;
/* 31 */     this.writer = new PrintWriter(new java.io.OutputStreamWriter(this.buffer, getCharacterEncoding()));
/*    */     
/* 33 */     return this.writer;
/*    */   }
/*    */   
/*    */   public ServletOutputStream getOutputStream() throws IOException
/*    */   {
/* 38 */     if (this.outputType == 1)
/* 39 */       throw new IllegalStateException();
/* 40 */     if (this.outputType == 2) {
/* 41 */       return this.output;
/*    */     }
/* 43 */     this.outputType = 2;
/* 44 */     this.output = new WrappedOutputStream(this.buffer);
/* 45 */     return this.output;
/*    */   }
/*    */   
/*    */   public void flushBuffer() throws IOException
/*    */   {
/* 50 */     if (this.outputType == 1)
/* 51 */       this.writer.flush();
/* 52 */     if (this.outputType == 2)
/* 53 */       this.output.flush();
/*    */   }
/*    */   
/*    */   public void reset() {
/* 57 */     this.outputType = 0;
/* 58 */     this.buffer.reset();
/*    */   }
/*    */   
/*    */   public byte[] getResponseData() throws IOException {
/* 62 */     flushBuffer();
/* 63 */     return this.buffer.toByteArray();
/*    */   }
/*    */   
/*    */   class WrappedOutputStream extends ServletOutputStream
/*    */   {
/*    */     private ByteArrayOutputStream buffer;
/*    */     
/*    */     public WrappedOutputStream(ByteArrayOutputStream buffer) {
/* 71 */       this.buffer = buffer;
/*    */     }
/*    */     
/*    */     public void write(int b) throws IOException {
/* 75 */       this.buffer.write(b);
/*    */     }
/*    */     
/*    */     public byte[] toByteArray() {
/* 79 */       return this.buffer.toByteArray();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Administrator\.m2\repository\org\jeecgframework\codegenerate\3.6.1-SNAPSHOT\codegenerate-3.6.1-20160919.020334-2.jar!\org\jeecgframework\core\aop\Wrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */