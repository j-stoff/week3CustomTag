package greetingTagLib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class HolidayGreetingTag extends SimpleTagSupport {

    @Override
    public void doTag() throws JspException, IOException {
        super.doTag();
        LocalDate current = LocalDate.now();
        JspWriter out = getJspContext().getOut();

        String holiday = determineHoliday(current);

        if (holiday.isEmpty()) {
            out.println("No Holiday today");
            out.println(current.getDayOfWeek().toString());
        } else {
            out.println(holiday);
        }

    }


    private String determineHoliday(LocalDate current) {
        String greeting = "";
        int dayNumber = current.getDayOfYear();
        int currentMonth = current.getMonthValue();
        DayOfWeek currentDay = current.getDayOfWeek();


        switch (currentMonth) {
            case 1: if (dayNumber == 1) {
                greeting = "Happy New Year!";
                break;
            }

            case 2: if (dayNumber == 14) {
                greeting = "Happy Valentine's Day";
                break;
            }

            case 4: if (dayNumber == 1) {
                greeting = "April Fool's!!!, maybe.";
                break;
            }
            case 10: if (dayNumber == 31) {
                greeting = "Happy Halloween";
                break;
            }
            case 11: if (dayNumber > 23 && currentDay.equals(DayOfWeek.THURSDAY)) {
                greeting = "Happy Thanksgiving";
            }
            case 12: if (dayNumber == 25) {
                greeting = "Merry Christmas";
                break;
            }
        }

        return greeting;

    }
}
