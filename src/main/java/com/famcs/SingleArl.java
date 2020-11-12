package famcs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class SingleArl {

        private int unicID;
        private String name;
        private String number;
        private String model;
        private Date date;
        private int amount;
        private float qControl;

        public static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        SingleArl(int unicID, String name, String number,
                String model, Date date, int amount, float qControl) {

            this.unicID = unicID;
            this.name = name;
            this.number = number;
            this.model = model;
            this.date = date;
            this.amount = amount;
            this.qControl = qControl;
        }

        SingleArl (String[] args) throws ParseException, IllegalArgumentException {

            if (args.length != 7) {
                throw new IllegalArgumentException("Error: wrong number of fields in the argument");
            }

            try {
                this.unicID = Integer.parseInt(args[0]);
                this.name = args[1];
                this.number = args[2];
                this.model = args[3];
                this.date = dateFormat.parse(args[4]);
                this.amount = Integer.parseInt(args[5]);
                this.qControl = Float.parseFloat(args[6]);
            }

            catch (Exception ex) {
                System.out.println(ex);
            }

        }

        @Override
        public String toString() {
            return unicID + ";" + name + ";" + number + ";"
                    + model + ";" + date.toString() + ";"
                    + amount + ";" + qControl + ";";
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleArl singleArl = (SingleArl) o;
        return unicID == singleArl.unicID &&
                amount == singleArl.amount &&
                Float.compare(singleArl.qControl, qControl) == 0 &&
                Objects.equals(name, singleArl.name) &&
                Objects.equals(number, singleArl.number) &&
                Objects.equals(model, singleArl.model) &&
                Objects.equals(date, singleArl.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unicID, name, number, model, date, amount, qControl);
    }

    void setUnicID(int uID) {
            this.unicID = uID;
        }

        int getUnicID() {
            return unicID;
        }

        void setName (String name) {
            this.name = name;

        }
        String getName() {
            return name;
        }

        void setNumber(String number) {
            this.number = number;
        }

        String getNumber() {
            return number;
        }

        void setModel(String model) {
            this.model = model;
        }

        String getModel() {
            return model;
        }

        void setDate(Date date) {
            this.date = date;
        }

        Date getDate() {
            return date;
        }

        void setAmount(int amount) {
            this.amount = amount;
        }

        int getAmount() {
            return amount;
        }

        void setqControl(float qControl) {
            this.qControl = qControl;
        }

        float getqControl() {
            return qControl;
        }



    }