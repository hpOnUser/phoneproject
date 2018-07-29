/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

// MESSAGE LOG_REQUEST_DATA PACKING
package com.MAVLink.common;
import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Messages.MAVLinkPayload;
        
/**
* Request a chunk of a log
*/
public class msg_log_request_data extends MAVLinkMessage{

    public static final int MAVLINK_MSG_ID_LOG_REQUEST_DATA = 119;
    public static final int MAVLINK_MSG_ID_LOG_REQUEST_DATA_CRC = 116;
    public static final int MAVLINK_MSG_LENGTH = 12;
    private static final long serialVersionUID = MAVLINK_MSG_ID_LOG_REQUEST_DATA;


      
    /**
    * Offset into the log
    */
    public long ofs;
      
    /**
    * Number of bytes
    */
    public long count;
      
    /**
    * Log id (from LOG_ENTRY reply)
    */
    public int id;
      
    /**
    * System ID
    */
    public short target_system;
      
    /**
    * Component ID
    */
    public short target_component;
    

    /**
    * Generates the payload for a mavlink message for a message of this type
    * @return
    */
    public MAVLinkPacket pack(){
        MAVLinkPacket packet = new MAVLinkPacket(MAVLINK_MSG_LENGTH);
        packet.sysid = 255;
        packet.compid = 190;
        packet.msgid = MAVLINK_MSG_ID_LOG_REQUEST_DATA;
        packet.crc_extra = MAVLINK_MSG_ID_LOG_REQUEST_DATA_CRC;
              
        packet.payload.putUnsignedInt(ofs);
              
        packet.payload.putUnsignedInt(count);
              
        packet.payload.putUnsignedShort(id);
              
        packet.payload.putUnsignedByte(target_system);
              
        packet.payload.putUnsignedByte(target_component);
        
        return packet;
    }

    /**
    * Decode a log_request_data message into this class fields
    *
    * @param payload The message to decode
    */
    public void unpack(MAVLinkPayload payload) {
        payload.resetIndex();
              
        this.ofs = payload.getUnsignedInt();
              
        this.count = payload.getUnsignedInt();
              
        this.id = payload.getUnsignedShort();
              
        this.target_system = payload.getUnsignedByte();
              
        this.target_component = payload.getUnsignedByte();
        
    }

    /**
    * Constructor for a new message, just initializes the msgid
    */
    public msg_log_request_data(){
        msgid = MAVLINK_MSG_ID_LOG_REQUEST_DATA;
    }

    /**
    * Constructor for a new message, initializes the message with the payload
    * from a mavlink packet
    *
    */
    public msg_log_request_data(MAVLinkPacket mavLinkPacket){
        this.sysid = mavLinkPacket.sysid;
        this.compid = mavLinkPacket.compid;
        this.msgid = MAVLINK_MSG_ID_LOG_REQUEST_DATA;
        unpack(mavLinkPacket.payload);
    }

              
    /**
    * Returns a string with the MSG name and data
    */
    public String toString(){
        return "MAVLINK_MSG_ID_LOG_REQUEST_DATA - sysid:"+sysid+" compid:"+compid+" ofs:"+ofs+" count:"+count+" id:"+id+" target_system:"+target_system+" target_component:"+target_component+"";
    }
}
        