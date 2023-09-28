package com.scienceminer.chatgpt;

public class Heart {
    private int heartRate;
    private boolean isContracting;
    private Chamber leftAtrium;
    private Chamber rightAtrium;
    private Chamber leftVentricle;
    private Chamber rightVentricle;
    private Artery pulmonaryArtery;
    private Artery aorta;
    private Vein pulmonaryVein;
    private Vein venaCava;

    public Heart() {
        this.heartRate = 60; // default heart rate in beats per minute
        this.isContracting = false;
        this.leftAtrium = new Chamber();
        this.rightAtrium = new Chamber();
        this.leftVentricle = new Chamber();
        this.rightVentricle = new Chamber();
        this.pulmonaryArtery = new Artery();
        this.aorta = new Artery();
        this.pulmonaryVein = new Vein();
        this.venaCava = new Vein();
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void contract() {
    	System.out.println("Heart contracting.");
         this.isContracting = true;
        pumpBlood();
    }
    

    public void stop() {
        // stop pumping action
        System.out.println("Heart has stopped.");
        System.exit(0); // terminate program
    }

    private void pumpBlood() {
        if (this.isContracting) {
            // move blood from atria to ventricles
            this.leftVentricle.receiveBlood(this.leftAtrium.sendBlood());
            this.rightVentricle.receiveBlood(this.rightAtrium.sendBlood());

            // pump blood from ventricles to arteries
            this.pulmonaryArtery.receiveBlood(this.rightVentricle.sendBlood());
            this.aorta.receiveBlood(this.leftVentricle.sendBlood());

            // receive blood from veins to atria
            this.leftAtrium.receiveBlood(this.pulmonaryVein.sendBlood());
            this.rightAtrium.receiveBlood(this.venaCava.sendBlood());

            // repeat pumping cycle
            try {
                Thread.sleep(60000 / this.heartRate); // delay based on heart rate
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pumpBlood();
        } else {
            return;
        }
    }
    
    public static void main(String[] args) {
        Heart heart = new Heart();
        heart.setHeartRate(60); // set heart rate to 60 bpm
        heart.contract(); // initiate pumping action
        
        // run for 10 iterations (approximately 10 seconds)
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000); // delay for 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        heart.stop(); // stop pumping action
    }

}

class Chamber {
    private Blood blood;

    public Chamber() {
        this.blood = new Blood();
    }

    public void receiveBlood(Blood blood) {
    	System.out.println(this.toString() + "receiving blood") ;
        
        this.blood = blood;
    }

    public Blood sendBlood() {
    	System.out.println(this.toString() + "sending blood") ;
        Blood temp = this.blood;
        this.blood = null;
        return temp;
    }
}

class Artery {
    private Blood blood;

    public Artery() {
        this.blood = null;
    }

    public void receiveBlood(Blood blood) {
        this.blood = blood;
    }

    public Blood sendBlood() {
        Blood temp = this.blood;
        this.blood = null;
        return temp;
    }
}

class Vein {
    private Blood blood;

    public Vein() {
        this.blood = new Blood();
    }

    public void receiveBlood(Blood blood) {
        this.blood = blood;
    }

    public Blood sendBlood() {
        Blood temp = this.blood;
        this.blood = new Blood();
        return temp;
    }
}

class Blood {
    // blood properties and methods
}
