package deerangle.treemendous.world;

public class BiomeSettings {
    private final float temperature;
    private final boolean snowy;
    private final boolean dry;
    private final int treeDensity;

    private BiomeSettings(float temp, boolean snowy, boolean dry, int treeDensity) {
        this.temperature = temp;
        this.snowy = snowy;
        this.dry = dry;
        this.treeDensity = treeDensity;
    }

    public float getTemperature() {
        return temperature;
    }

    public boolean isSnowy() {
        return snowy;
    }

    public boolean isDry() {
        return dry;
    }

    public int getTreeDensity() {
        return treeDensity;
    }

    public static class Builder {
        private float temperature;
        private boolean snowy;
        private boolean dry;
        private int treeDensity;

        public Builder() {
            this.temperature = 0.7f;
            this.snowy = false;
            this.dry = false;
            this.treeDensity = 10;
        }

        public Builder temperature(float temp) {
            this.temperature = temp;
            return this;
        }

        public Builder snow() {
            this.snowy = true;
            return this;
        }

        public Builder dry() {
            this.snowy = false;
            this.dry = true;
            return this;
        }

        public BiomeSettings build() {
            return new BiomeSettings(this.temperature, this.snowy, this.dry, this.treeDensity);
        }

        public Builder density(int density) {
            this.treeDensity = density;
            return this;
        }
    }
}
