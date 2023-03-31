package info.skyblond.typecho.mate.mail

import info.skyblond.typecho.mate.Comment

/**
 * Notify guest, thus the parent must not be null.
 * */
fun generateGuest(comment: Comment, parent: Comment): String = """
    <table cellpadding="0" cellspacing="0" border="0" height="100%" width="100%" bgcolor=#FFFFFF style="border-collapse:collapse;">
      <tr>
        <td><center style="width: 100%;">

    		<!-- Email Header : BEGIN -->
    		<table cellspacing="0" cellpadding="0" border="0" align="center" width="100%" style="max-width: 680px;">
                <tr>
                  <td style="text-align: center" width="50%">
    					<img src="https://statics.skyblond.info/email/logo.png" width="290">
    				</td>
                </tr>
              </table>
              <!-- Email Header : END --> 
              
              <!-- Email Body : BEGIN -->
              <table cellspacing="0" cellpadding="0" align="center" bgcolor="#ffffff" width="100%" style="max-width: 680px;">
    			  <tr>
    			  	<td style="padding: 5px 10px;width: 100%;font-size: 7px; font-family: sans-serif; mso-height-rule: exactly; line-height:13px; text-align: center; color: #999999;"><br>
    					这封邮件包含HTML样式及引用了远程图片。<br>
    					为获得最佳效果，请使用支持HTML的邮件客户端，及解除对引用远程元素的屏蔽。<br>
    					This email contains HTML and remote images.<br>
    					For the best performance, please using proper client and unblock remote images.
    				</td>
    			  </tr>
                <tr>
                  <td class="full-width-image" align="center">
    				<div style="background-color:white;border-top:2px solid #12ADDB;box-shadow:0 1px 3px #AAAAAA;line-height:180%;padding:0 15px 12px;width:680px;margin:50px auto;color:#555555;font-family:'Century Gothic','Trebuchet MS','Hiragino Sans GB',微软雅黑,'Microsoft Yahei',Tahoma,Helvetica,Arial,'SimSun',sans-serif;font-size:15px;">  
    					<h1 style="border-bottom:1px solid #DDD;font-size:19px;font-weight:normal;padding:13px 0 10px 8px;">
    					<span style="color: #12ADDB;font-weight: bold;">&gt; </span>您(${parent.commentAuthor})在<a style="text-decoration:none;color: #12ADDB;" href="${parent.commentPermalink}" target="_blank">《${parent.contentTitle}》</a>的评论有了新的回复</h1>  
    					<div style="padding:0 12px 0 12px;margin-top:18px;text-align: left">  
    					
    						<p>时间：<span style="border-bottom:1px dashed #ccc;" t="5" times=" 20:42">${comment.time}</span></p>  
              
    			<p>你的评论：</p>  
                <p style="background-color: #f5f5f5;border: 0px solid #DDD;padding: 10px 15px;margin:18px 0">${parent.commentText}</p>  
                <p><strong>${comment.commentAuthor}</strong>&nbsp;回复说：</p>  
                <p style="background-color: #f5f5f5;border: 0px solid #DDD;padding: 10px 15px;margin:18px 0">${comment.commentText}</p>  
                <p>您可以点击 <a style="text-decoration:none; color:#12addb" href="${comment.commentPermalink}" target="_blank">查看回复的完整內容 </a>，本邮件为自动发送，请勿直接回复。如有疑问，请联系我，电子邮件：<a style="text-decoration:none; color:#12addb"  href="mailto:hurui200320@skyblond.info" target="_blank">hurui200320@skyblond.info</a>，欢迎再次光临 <a style="text-decoration:none; color:#12addb" href="https://skyblond.info" target="_blank">天空Blond </a>。</p>  
    					
    					
    					
    					</div>  				
    					<div style="background-image: url(https://statics.skyblond.info/email/flower.png);background-repeat: no-repeat;background-position: top center;background-size: 140px 215px;text-align: right">  
    						<br>
    						<p align="right">天空 Blond<br>
    						<span times=" 20:42">${comment.time}</span></p>
    						<br><br>
    						<br><br><br><br><br>
           			 	</div>
    				</div>
    			  </td>
                </tr>            
              </table>
    			
              <!-- Email Footer : BEGIN -->
              <table cellspacing="0" cellpadding="0" border="0" align="center" width="100%" style="max-width: 680px;">
                <tr>
                  <td style="padding: 5px 10px;width: 100%;font-size: 12px; font-family: sans-serif; mso-height-rule: exactly; line-height:18px; text-align: center; color: #888888;"><br>
                    <br>
                    天空 Blond © 2018<br>
                    hurui200320@skyblond.info<br>
    				<a style="text-decoration:none; color:#12addb" href="天空 Blond" target="_blank">https://skyblond.info</a>
    				</td>
                </tr>
              </table>
              <!-- Email Footer : END --> 
             
            
          </center></td>
      </tr>
    </table>
""".trimIndent()

fun generateOwner(comment: Comment): String = """
    <div style="background-color:white;border-top:2px solid #12ADDB;box-shadow:0 1px 3px #AAAAAA;line-height:180%;padding:0 15px 12px;width:500px;margin:50px auto;color:#555555;font-family:'Century Gothic','Trebuchet MS','Hiragino Sans GB',微软雅黑,'Microsoft Yahei',Tahoma,Helvetica,Arial,'SimSun',sans-serif;font-size:12px;">  
        <h2 style="border-bottom:1px solid #DDD;font-size:14px;font-weight:normal;padding:13px 0 10px 8px;"><span style="color: #12ADDB;font-weight: bold;">&gt; </span>(${comment.commentAuthor})在<a style="text-decoration:none;color: #12ADDB;" href="${comment.commentPermalink}" target="_blank">《${comment.contentTitle}》</a>创造了新的评论</h2>  
        <div style="padding:0 12px 0 12px;margin-top:18px">  
            <p>时间：<span style="border-bottom:1px dashed #ccc;" t="5" times=" 20:42">${comment.time}</span></p>  
            <p style="background-color: #f5f5f5;border: 0px solid #DDD;padding: 10px 15px;margin:18px 0">${comment.commentText}</p>  
            <p>评论者"${comment.commentAuthor}"邮箱为:</p>  
            <p style="background-color: #f5f5f5;border: 0px solid #DDD;padding: 10px 15px;margin:18px 0"> <a href="mailto:${comment.commentAuthorMail}" target="_blank">${comment.commentAuthorMail}</a></p>
            <p>评论者"${comment.commentAuthor}"其他信息:</p>  
            <p style="background-color: #f5f5f5;border: 0px solid #DDD;padding: 10px 15px;margin:18px 0">IP：${comment.commentAuthorIP}，状态：${comment.commentStatus}</p>   
            <p>您可以点击 <a style="text-decoration:none; color:#12addb" href="${comment.commentPermalink}" target="_blank">查看回复的完整內容 </a>。</p>  
        </div>  
    </div>
""".trimIndent()
