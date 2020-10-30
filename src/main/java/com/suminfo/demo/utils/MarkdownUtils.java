package com.suminfo.demo.utils;

import org.commonmark.ext.gfm.tables.TableBlock;
import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Map;

public class MarkdownUtils {
    /**
     * markdown格式转化html格式
     */
    public static  String markdownToHtml(String markdown){
        Parser parse = Parser.builder().build();
        Node document =parse.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().build();

        return renderer.render(document);

    }
    /**
     * 增加扩展(标题锚地，表格生成)
     * H
     */



    /**
     * 处理标签的属性
     */
    static  class CustomAttributeProvider implements AttributeProvider{
        @Override
        public void setAttributes(Node node, String s, Map<String, String> map) {
            //改变a便签的target属性为_blank
            if(node instanceof Link){
                attributes.put("target","_blank");
            }
            if(node instanceof TableBlock){
                attributes.put("class","ui celled table");

            }
        }
    }

}
