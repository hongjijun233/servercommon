package com.lianghongji.util;

import java.util.Map;

/**
 * 替换字符串中某些参数形成新的字符串
 * 
 * @author chi.weineng
 *
 */
public class DynamicString {
	private String dynStr;  
    private DynamicPattern header;  
    private DynamicPattern tail;  
  
    static abstract class DynamicPattern {  
        DynamicPattern next;  
        public abstract Object subConvert(Map<String,?> pattern);  
    }  
  
    static class StringPattern extends DynamicPattern {  
        private String pattern;  
  
        public StringPattern(StringBuilder buff, int start, int end) {  
            this.pattern = buff.substring(start, end);  
        }  
        @Override  
        public Object subConvert(Map<String,? extends Object> map) {  
            return pattern;  
        }  
    }  
  
    static class MappedPattern extends DynamicPattern {  
        private String key;  
  
        public MappedPattern(StringBuilder buff, int start, int end) {  
            this.key = buff.substring(start, end);  
        }  
        @Override  
        public Object subConvert(Map<String,? extends Object> param) {  
            if(param.get(key)==null){  
                throw new IllegalArgumentException("所传入的Map中，不含有参数："+key);  
            }  
            return param.get(key);  
        }  
    }  
  
    /**可替换参数变量的字符串类。该类适用于多个参数的情况。 
     * @param dynStr 模板字符串。 
     */  
    public DynamicString(String dynStr) {  
        this.dynStr = dynStr;  
        init();  
    }  
  
    /** 
     * 生成中间模板，转义字符也要考虑在内。 
     */  
    private void init() {  
        header = tail = null;  
        StringBuilder buff = new StringBuilder(dynStr);  
        int start = 0, ptr = 0;  
        boolean noMatching = true;  
        for (ptr = start; ptr < buff.length(); ptr++) {  
            if(buff.charAt(ptr)=='$' && buff.charAt(ptr+1)=='{'){  
                if(ptr>0 && buff.charAt(ptr-1)=='/'){  
                    buff.deleteCharAt(ptr---1);  
                    if(ptr>1 && buff.charAt(ptr-1)=='\\' && buff.charAt(ptr-2)=='\\'){  
                        buff.deleteCharAt(ptr---1);continue;  
                    }  
                    if(!(ptr>0 && buff.charAt(ptr-1)=='\\'))  
                    continue;  
                }  
                noMatching=false;  
                StringPattern sp = new StringPattern(buff, start, ptr);  
                appendPattern(sp);  
                start = ptr+2;  
                for (ptr += 2; ptr < buff.length(); ptr++) {  
                    if (buff.charAt(ptr) == '}') {  
                        if(buff.charAt(ptr-1)=='\\'){  
                            buff.deleteCharAt(ptr---1);  
                            if(buff.charAt(ptr-1)!='\\')  
                            continue;  
                        }  
                        MappedPattern mp = new MappedPattern(buff, start, ptr);  
                        appendPattern(mp);  
                        noMatching=true;  
                        start = ++ptr;break;  
                    }  
                }  
            }  
        }  
        if (noMatching && ptr <= buff.length())  
            appendPattern(new StringPattern(buff, start, ptr));  
    }  
  
    private DynamicString appendPattern(DynamicPattern pattern) {  
        if (header == null) {  
            header = pattern;  
            tail = header;  
        } else {  
            tail.next = pattern;  
            tail = pattern;  
        }  
        return this;  
    }  
  
    /**传入参数变量，得到替换后的结果。 
     * @param param 将替换的参数及变量以键值对的形式存放到Map对象中。 
     * @return  返回替换回的结果。 
     * @exception IllegalArgumentException 当待替换的参数不在Map对象中时，抛出该异常。 
     */  
    public String convert(Map<String,? extends Object> param) {  
        if (header == null)  
            return null;  
        DynamicPattern ptr = header;  
        StringBuilder sb = new StringBuilder();  
        while (ptr != null) {  
            sb.append(ptr.subConvert(param));  
            ptr = ptr.next;  
        }  
        return sb.toString();  
    }  
  
    /** 
     * @see #convert(Map) 
     */  
    public String convert(String key,String value){  
        Map<String,String> param = new java.util.HashMap<String,String>(2);  
        param.put(key, value);  
        return convert(param);  
    }  
}
