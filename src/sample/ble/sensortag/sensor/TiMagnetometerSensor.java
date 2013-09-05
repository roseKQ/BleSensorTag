package sample.ble.sensortag.sensor;

import android.bluetooth.BluetoothGattCharacteristic;

/**
 * Created by steven on 9/3/13.
 */
public class TiMagnetometerSensor extends TiSensor<float[]> {

    public static final TiMagnetometerSensor INSTANCE = new TiMagnetometerSensor();

    private TiMagnetometerSensor() {
        super();
    }

    @Override
    public String getName() {
        return "Magnetometer";
    }

    @Override
    public String getServiceUUID() {
        return "f000aa30-0451-4000-b000-000000000000";
    }

    @Override
    public String getDataUUID() {
        return "f000aa31-0451-4000-b000-000000000000";
    }

    @Override
    public String getConfigUUID() {
        return "f000aa32-0451-4000-b000-000000000000";
    }
    //TODO: add period config option

    @Override
    public String toString(BluetoothGattCharacteristic c) {
        final float[] data = onCharacteristicChanged(c);
        return "x="+data[0]+"\ny="+data[1]+"\nz="+data[2];
    }

    public float[] onCharacteristicChanged(BluetoothGattCharacteristic c) {
        // Multiply x and y with -1 so that the values correspond with our pretty pictures in the app.
        float x = shortSignedAtOffset(c, 0) * (2000f / 65536f) * -1;
        float y = shortSignedAtOffset(c, 2) * (2000f / 65536f) * -1;
        float z = shortSignedAtOffset(c, 4) * (2000f / 65536f);

        return new float[]{x, y, z};
    }

}
