package com.connectsdk.service.airplay;

import androidx.exifinterface.media.ExifInterface;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class PListBuilder {

    Document doc;
    DocumentType dt;
    Element root;
    Element rootDict;

    public PListBuilder() {
        try {
            DOMImplementation dOMImplementation = DocumentBuilderFactory.newInstance().newDocumentBuilder().getDOMImplementation();
            DocumentType createDocumentType = dOMImplementation.createDocumentType(PListParser.TAG_PLIST, "-//Apple//DTD PLIST 1.0//EN", "http://www.apple.com/DTDs/PropertyList-1.0.dtd");
            this.dt = createDocumentType;
            Document createDocument = dOMImplementation.createDocument("", PListParser.TAG_PLIST, createDocumentType);
            this.doc = createDocument;
            createDocument.setXmlStandalone(true);
            Element documentElement = this.doc.getDocumentElement();
            this.root = documentElement;
            documentElement.setAttribute("version", "1.0");
            Element createElement = this.doc.createElement(PListParser.TAG_DICT);
            this.rootDict = createElement;
            this.root.appendChild(createElement);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void putKey(String key) {
        Element createElement = this.doc.createElement("key");
        createElement.setTextContent(key);
        this.rootDict.appendChild(createElement);
    }

    public void putString(String key, String value) {
        putKey(key);
        Element createElement = this.doc.createElement("string");
        createElement.setTextContent(value);
        this.rootDict.appendChild(createElement);
    }

    public void putReal(String key, double value) {
        putKey(key);
        Element createElement = this.doc.createElement(PListParser.TAG_REAL);
        createElement.setTextContent(String.valueOf(value));
        this.rootDict.appendChild(createElement);
    }

    public void putInteger(String key, long value) {
        putKey(key);
        Element createElement = this.doc.createElement(PListParser.TAG_INTEGER);
        createElement.setTextContent(String.valueOf(value));
        this.rootDict.appendChild(createElement);
    }

    public void putBoolean(String key, boolean value) {
        putKey(key);
        this.rootDict.appendChild(this.doc.createElement(value ? PListParser.TAG_TRUE : PListParser.TAG_FALSE));
    }

    public void putData(String key, String value) {
        putKey(key);
        Element createElement = this.doc.createElement("data");
        createElement.setTextContent(value);
        this.rootDict.appendChild(createElement);
    }

    public String toString() {
        DOMSource dOMSource = new DOMSource(this.doc);
        TransformerFactory newInstance = TransformerFactory.newInstance();
        StringWriter stringWriter = new StringWriter();
        try {
            Transformer newTransformer = newInstance.newTransformer();
            newTransformer.setOutputProperty("encoding", "UTF-8");
            newTransformer.setOutputProperty("doctype-public", this.dt.getPublicId());
            newTransformer.setOutputProperty("doctype-system", this.dt.getSystemId());
            newTransformer.setOutputProperty("indent", "yes");
            newTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", ExifInterface.GPS_MEASUREMENT_2D);
            newTransformer.transform(dOMSource, new StreamResult(stringWriter));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e2) {
            e2.printStackTrace();
        }
        return stringWriter.toString();
    }
}
