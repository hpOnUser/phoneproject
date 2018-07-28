/* AUTO-GENERATED FILE.  DO NOT MODIFY.
 *
 * This class was automatically generated by the
 * java mavlink generator tool. It should not be modified by hand.
 */

package com.MAVLink.enums;

/** 
* Flags in EKF_STATUS message
*/
public class ESTIMATOR_STATUS_FLAGS {
   public static final int ESTIMATOR_ATTITUDE = 1; /* True if the attitude estimate is good | */
   public static final int ESTIMATOR_VELOCITY_HORIZ = 2; /* True if the horizontal velocity estimate is good | */
   public static final int ESTIMATOR_VELOCITY_VERT = 4; /* True if the  vertical velocity estimate is good | */
   public static final int ESTIMATOR_POS_HORIZ_REL = 8; /* True if the horizontal position (relative) estimate is good | */
   public static final int ESTIMATOR_POS_HORIZ_ABS = 16; /* True if the horizontal position (absolute) estimate is good | */
   public static final int ESTIMATOR_POS_VERT_ABS = 32; /* True if the vertical position (absolute) estimate is good | */
   public static final int ESTIMATOR_POS_VERT_AGL = 64; /* True if the vertical position (above ground) estimate is good | */
   public static final int ESTIMATOR_CONST_POS_MODE = 128; /* True if the EKF is in a constant position mode and is not using external measurements (eg GPS or optical flow) | */
   public static final int ESTIMATOR_PRED_POS_HORIZ_REL = 256; /* True if the EKF has sufficient data to enter a mode that will provide a (relative) position estimate | */
   public static final int ESTIMATOR_PRED_POS_HORIZ_ABS = 512; /* True if the EKF has sufficient data to enter a mode that will provide a (absolute) position estimate | */
   public static final int ESTIMATOR_GPS_GLITCH = 1024; /* True if the EKF has detected a GPS glitch | */
   public static final int ESTIMATOR_ACCEL_ERROR = 2048; /* True if the EKF has detected bad accelerometer data | */
   public static final int ESTIMATOR_STATUS_FLAGS_ENUM_END = 2049; /*  | */
}
            