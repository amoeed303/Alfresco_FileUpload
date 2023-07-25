package com.example;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
public class Alfresco {
public static void main(String[] args) {
// default factory implementation
  SessionFactory factory = SessionFactoryImpl.newInstance();
  Map<String, String> parameter = new HashMap<String, String>();
// user credentials
  parameter.put(SessionParameter.USER, "admin");
  parameter.put(SessionParameter.PASSWORD, "admin");
// connection settings
  parameter.put(SessionParameter.ATOMPUB_URL, "http://127.0.0.1:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
  parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());
// create session
  Session session = factory.getRepositories(parameter).get(0).createSession();
  Folder root = session.getRootFolder();
// properties
  Map<String, Object> properties = new HashMap<String, Object>();
  properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
  properties.put(PropertyIds.NAME, "MyAlfrescoFolder"); // folder name
// create the folder
  Folder parent = root.createFolder(properties);
String name = "NewTextFile.txt";
// properties
  Map<String, Object> properties2 = new HashMap<String, Object>();
  properties2.put(PropertyIds.OBJECT_TYPE_ID, "cmis:document");
  properties2.put(PropertyIds.NAME, name);
// content
  byte[] content = "Hello Code Factory...!".getBytes();
  InputStream stream = new ByteArrayInputStream(content);
  ContentStream contentStream = new ContentStreamImpl(name, BigInteger.valueOf(content.length), "text/plain", stream);
// create a major version
  Document newDoc = parent.createDocument(properties2, contentStream, VersioningState.MAJOR);
System.out.println("DONE.");
}
}