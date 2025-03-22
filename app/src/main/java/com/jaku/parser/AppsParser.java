package com.jaku.parser;

import com.jaku.core.Response;
import com.jaku.model.Channel;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class AppsParser extends JakuParser {
    public Object parse(Response response) {
        ArrayList<Channel> arrayList = new ArrayList<>();
        if (!(response == null || response.getData() == null)) {
            try {
                List<Element> children = new SAXBuilder().build((Reader) new StringReader(response.getData().toString())).getRootElement().getChildren();
                for (int i = 0; i < children.size(); i++) {
                    Element element = children.get(i);
                    if (element.getAttribute("id") != null) {
                        Channel channel = new Channel();
                        channel.setId(element.getAttribute("id").getValue());
                        channel.setTitle(element.getValue());
                        channel.setType(element.getAttribute("type").getValue());
                        channel.setVersion(element.getAttribute("version").getValue());
                        arrayList.add(channel);
                    }
                }
            } catch (JDOMException | IOException e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }
}
