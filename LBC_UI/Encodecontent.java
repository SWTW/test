package jsoup;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
//寮曞叆閿欏寘锛屽垹鎺変箣鍓嶇殑

public class Encodecontent { 
	  public static byte[] decode(final byte[] bytes) {  
	      return Base64.decodeBase64(bytes);  
	  }  
	  public static String encode(final byte[] bytes) {  
	      return new String(Base64.encodeBase64(bytes));  
	  } 
	  public String reContent(String input) throws UnsupportedEncodingException{
		  String result="";
		  byte[] decodeBase64 = Base64.decodeBase64(new String(input).getBytes());
			//    System.out.println("RESULT: "+new String(decodeBase64,"UTF-8"));
			    
			    /*绗竴绉嶆柟娉�
			    String[] utf16 = new String[decodeBase64.length];
			    for (int i = 0,j=0; i < decodeBase64.length; i++) {    
			      utf16[j] = Integer.toHexString(decodeBase64[i] & 0xFF);    
			      if (utf16[j].length() == 1) {    
			        utf16[j] = '0' + utf16[j];    
			      }
			      System.out.println(utf16[j]);  
			      j++;
			    }    
			    String[] b = new String[decodeBase64.length/2];
			    for (int i = 0,j=0; i < decodeBase64.length; i=i+2) {   
			      String a = utf16[i+1]+utf16[i];
			      int data = Integer.parseInt(a, 16);
			      System.out.print((char)data);
			    }*/
			    //绗簩绉嶆柟娉�
			    byte temp;
			    for(int i=0;i<decodeBase64.length;i=i+2){
			      temp = decodeBase64[i];
			      decodeBase64[i]=decodeBase64[i+1];
			      decodeBase64[i+1]=temp;
			    }
		//	    result=new String(decodeBase64,"utf-16");
			   result = Encodecontent.jiexiString(new String(decodeBase64,"utf-16"));
		//	    System.out.print(result);
		  
		  return result;
	  }
	  public static void main(String[] args) throws Exception {
	    String input = ">PABoAHQAbQBsAD4APABiAG8AZAB5AD4APABQAD4AV1P9kK+LsIsFgMSeYE4fT1l1iFs/UeV6L2bRj3ReZWcATipOCGE+ZoF6+lGEdu6VmJga/9ZO7E6EdjZyzWs6ToZOH3WhixZZ+lFTYuVdDP9GT1xPOk5QW3NZhHbWTuxOdFNZdShXhk6cUVFntlvMkQz/1k7sToR2EGJ/lQ5OWWWygO6VmJgVX9FTf17bbMVi518M/0ZPiF8RXAlnuk5VXABfB1KeW0yIqFICMABO9Hb9j0JsAU8aTg5OPnkaT4xUEIzRU1VchHadW2yafWxmjwz/Y2soV55bvWUATnmYc1HobFl1iFs/UeV6hHYcIKZg+4uhixJSHSAM/xqQx4/Efsd+z34AlUZVjFQxcsNfZo87TlBjYI3+VmZODP/Vi/5Wnlg6Xz55Gk/5W1l1iFs/UeV6hHZzUehsjFR1VKRiDP/zgcpO8l3fUsaW/lZmTjEANAA0ADAAMACMUQIwDQAKADwAUAA+ADEAMgAIZzIANADlZS0AMgA2AOVlDP9CAE0AVwAcIOtfUE4FlvuLaFQrZx0gO22oUmVnMFKKf85Xf17eXQz/GpDHj1BjYI0xcsNf/lZmTiAAATBaU2lyhpnCU8KJSXswTsxbGllpX4R2O22oUoVRuVsM/41Rpl5HbFqABVPsYs9+AJVGVQEwMXLDX2aPO05JeyhXhVGEdp1bbJoxcsNfJ1m2W61ehHabUs+RDP86Tn9e3l0CXsSe1Fc6UwxUwU5mWyFohHYxADgADVRZdYhbP1HleiZeu1NzUTFyjFR1VKRiDP8uXqlSaVtQW+xOKFfTW1llvGVQToR2uWUPXwtO73mBZ3Js1lPld8aLDP+rjsNfZVC3XjBX619QThBif5UCMA0ACgA8AFAAPgAKTmhUK2edW2yaHCDrX1BOBZb7i2hUK2cdIAxU9mUoVylZJW0BMApOd22MVH+JiVszACdZzlcCXgpOFG8CMABOLGcsZ39ifY9AdzFyw1+Edv5WZk4M/yhX2Y8qTqxRKVkM/zpO0Y9+dg1UWXWIWz9R5XomXrtThk7gZVCWhHZzUTFyAjBkloZO/lZmTiAAUGNgjQz/aVtQW+xO2I8oVzFyw19mjztOylMMVISfD1wZTzRPhHZqljRPC04M/0V1OG5aU2lyhpkBMJJjFG8cIP6LLGdnUh0gATAoVyJr8FgRe+2L9JUATgxUImumXiNX3ouCggz/Ok7CUw5OdlEtToR2z2sATipOuk79kFl1C06GTo5/fVneVsZfAjANAAoAPABQAD4APAAvAFAAPgA8AC8AYgBvAGQAeQA+ADwALwBoAHQAbQBsAD4A"; 
	    //byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
	   // System.out.println("RESULT: " + new String(encodeBase64));
	    Encodecontent news = new Encodecontent();
	    news.reContent(input);
	  }
	  public static String jiexiString(String Sfromurl){
			StringBuilder resultroom =new StringBuilder(); //浣跨敤stringbuilder鍙互鐩存帴杩藉姞鍐呭鍒板叾涓婏紝鑰屼笉鐢ㄥ彟璧凤紝闃叉婧㈠嚭锛岃妭绾﹀唴瀛樸��
			// 鎶婅鍖归厤鐨勫瓧绗︿覆鍐欐垚姝ｅ垯琛ㄨ揪寮忥紝鐒跺悗瑕佹彁鍙栫殑瀛楃浣跨敤鎷彿鎷捣鏉�
			Pattern pattern=null;			
			pattern = Pattern.compile("(?<=<P>)(.*)(?=。)");	
			Matcher matcher = pattern.matcher(Sfromurl);
			while(matcher.find()){
				String e=matcher.group();
				resultroom.append(e);			
			}
			String result=resultroom.toString();	
			return result;
			}
	}

