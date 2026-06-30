
package com.restaurant.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.restaurant.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckLoginDB_QNAME = new QName("http://services.restaurant.com/", "checkLoginDB");
    private final static QName _CheckLoginDBResponse_QNAME = new QName("http://services.restaurant.com/", "checkLoginDBResponse");
    private final static QName _Hello_QNAME = new QName("http://services.restaurant.com/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://services.restaurant.com/", "helloResponse");
    private final static QName _RegisterDB_QNAME = new QName("http://services.restaurant.com/", "registerDB");
    private final static QName _RegisterDBResponse_QNAME = new QName("http://services.restaurant.com/", "registerDBResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.restaurant.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CheckLoginDB }
     * 
     */
    public CheckLoginDB createCheckLoginDB() {
        return new CheckLoginDB();
    }

    /**
     * Create an instance of {@link CheckLoginDBResponse }
     * 
     */
    public CheckLoginDBResponse createCheckLoginDBResponse() {
        return new CheckLoginDBResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link RegisterDB }
     * 
     */
    public RegisterDB createRegisterDB() {
        return new RegisterDB();
    }

    /**
     * Create an instance of {@link RegisterDBResponse }
     * 
     */
    public RegisterDBResponse createRegisterDBResponse() {
        return new RegisterDBResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLoginDB }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckLoginDB }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "checkLoginDB")
    public JAXBElement<CheckLoginDB> createCheckLoginDB(CheckLoginDB value) {
        return new JAXBElement<CheckLoginDB>(_CheckLoginDB_QNAME, CheckLoginDB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLoginDBResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CheckLoginDBResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "checkLoginDBResponse")
    public JAXBElement<CheckLoginDBResponse> createCheckLoginDBResponse(CheckLoginDBResponse value) {
        return new JAXBElement<CheckLoginDBResponse>(_CheckLoginDBResponse_QNAME, CheckLoginDBResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterDB }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterDB }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "registerDB")
    public JAXBElement<RegisterDB> createRegisterDB(RegisterDB value) {
        return new JAXBElement<RegisterDB>(_RegisterDB_QNAME, RegisterDB.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterDBResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link RegisterDBResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://services.restaurant.com/", name = "registerDBResponse")
    public JAXBElement<RegisterDBResponse> createRegisterDBResponse(RegisterDBResponse value) {
        return new JAXBElement<RegisterDBResponse>(_RegisterDBResponse_QNAME, RegisterDBResponse.class, null, value);
    }

}
