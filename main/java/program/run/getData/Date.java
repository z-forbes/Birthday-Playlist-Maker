package program.run.getData;

public class Date {

    private int day;
    private int month;
    private int year;

    // ALL INDEXING FROM 1
    public Date(int day, int month, int year) {
        try {
            this.day = day;
            this.month = month;
            this.year = year;
            validateDate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date provided");
        }

    }

    private static final String dateSeparator = "-";
    // dateFormat = "<year>" + dateSeparator + "<month>" + dateSeparator + "<day>";

    public Date(String formattedDate) {
        try {
            String[] splitDate = formattedDate.replace(dateSeparator, "-").split("-");
            if (splitDate.length != 3) {
                throw new IllegalArgumentException();
            }
            this.year = Integer.parseInt(splitDate[0]);
            this.month = Integer.parseInt(splitDate[1]);
            this.day = Integer.parseInt(splitDate[2]);
            validateDate();
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid data provided");
        }
    }

    private void validateDate() {
        if ((this.day<=0) || (this.month<=0) || (this.year<=0)) {
            throw new IllegalArgumentException();
        }
        if (("" + this.year).length() != 4) {
            throw new IllegalArgumentException();
        }
    }

    /** returns formatted date for use in the chart URL **/
    public String getFormattedDate() {
        String output = "" + year;
        if (month<10) {
            output += "0";
        }
        output += month;
        if (day<10) {
            output += "0";
        }
        return output + day;
    }

    /** returns a well-formatted string of the date **/
    public String getPrettyDate() {
        String daySuffix;
        String dayEnd = ("" + day).substring(("" + day).length()-1);
        switch (dayEnd.charAt(0)) {
            case '1':
                daySuffix = "st";
                break;

            case '2':
                daySuffix = "nd";
                break;

            case '3':
                daySuffix = "rd";
                break;

            default:
                daySuffix = "th";
        }

        String monthName;
        switch (month) {
            case 1:  monthName = "January";
                break;
            case 2:  monthName = "February";
                break;
            case 3:  monthName = "March";
                break;
            case 4:  monthName = "April";
                break;
            case 5:  monthName = "May";
                break;
            case 6:  monthName = "June";
                break;
            case 7:  monthName = "July";
                break;
            case 8:  monthName = "August";
                break;
            case 9:  monthName = "September";
                break;
            case 10: monthName = "October";
                break;
            case 11: monthName = "November";
                break;
            case 12: monthName = "December";
                break;
            default: throw new IllegalArgumentException("Invalid month of: " + month);
        }

        return monthName + " " + day + daySuffix + " '" + ("" + year).substring(2);
    }

    public String getYear() {return "" + year; }
}
