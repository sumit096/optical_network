public class Fiber {
    private String name;
    private final int noOfWavLens = 2;
    private int direction;
    private int[] wavLens;
    private int consumedWavLens = 0;
    private final int capacityPerWavLen = 40;

    public Fiber(String name, int direction) {
        this.name = name;
        this.direction = direction;
        wavLens = new int[noOfWavLens];
    }

    public int getDirection() {
        return direction;
    }

    public int getCapacityPerWavLen() {
        return this.capacityPerWavLen;
    }

    public int getConsumedWavLens() {
        return this.consumedWavLens;
    }

    public boolean isWavLensFree() {
        return this.noOfWavLens > this.consumedWavLens;
    }

    public int reqWavLen(int wavLen) {
        if (isWavLensFree()) {
            if (this.wavLens[wavLen] == 0) {
                this.wavLens[wavLen] = 1;
                this.consumedWavLens++;

                return wavLen;
            } else {
                for (int i = wavLen + 1; i < this.noOfWavLens; i++) {
                    if (this.wavLens[i] == 0) {
                        this.wavLens[i] = 1;
                        this.consumedWavLens++;

                        return  i;
                    }
                }
            }
        }

        return -1;
    }

    public void releaseWavLen(int wavLen) {
        this.wavLens[wavLen] = 0;
        this.consumedWavLens--;
    }

    public boolean consumeWavLen() {
        if (isWavLensFree()) {
            if (this.wavLens[this.consumedWavLens] == 0) {
                this.wavLens[this.consumedWavLens] = 1;
                this.consumedWavLens++;

                return true;
            }
        }

        return false;
    }
}
