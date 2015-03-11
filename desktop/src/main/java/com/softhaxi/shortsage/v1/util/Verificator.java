package com.softhaxi.shortsage.v1.util;

public class Verificator {
    /**
     * Reference <a href="http://stackoverflow.com/questions/19266277/java-for-smslib-building-error-for-sending-message-using-smslib">Check Port List</a>
     *
     */
    public static void checkPortList() {
        // List portNames
        Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier portId = ports.nextElement();
            String s = getPortInfo(portId);
            System.out.println(s);
        }

        // test open-close methods on each port
        ports = CommPortIdentifier.getPortIdentifiers();
        while (ports.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier)ports.nextElement();
            CommPort port = null;
            try {
                System.out.print("open " + portId.getName());
                port = portId.open(ListPorts.class.getName(), 2000);
                port.close();
                System.out.println("...closed");                
            } catch (Exception ex) {
                ex.printStackTrace();
                if (port != null)
                    try { port.close(); } catch (Exception e) { }
            }
        }      
    }
    
    private static String getPortInfo(CommPortIdentifier portid) {
        StringBuilder sb = new StringBuilder();
        sb.append(portid.getName());
        sb.append(", ");
        sb.append("portType: ");
        if (portid.getPortType() == CommPortIdentifier.PORT_SERIAL)
            sb.append("serial");
        else if (portid.getPortType() == CommPortIdentifier.PORT_PARALLEL)
            sb.append("parallel");
        else
            sb.append("unknown");
        return sb.toString();
    }
}
