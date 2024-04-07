package task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class DateTask {
    public static void main(String[] args){
        System.out.println("Введите дату в формате dd.MM.yyyy:");
        Scanner in = new Scanner(System.in);
        String strDate = in.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date;
        try{
            date = dateFormat.parse(strDate);
        }catch (ParseException e){
            System.out.println("Неверный формат даты");
            return;
        }

        Calendar firstCalendarDate = new GregorianCalendar();
        firstCalendarDate.setTime(date);
        firstCalendarDate.add(Calendar.DATE,45);
        System.out.println("Дата после увеличения на 45 дней: " + dateFormat.format(firstCalendarDate.getTime()));

        Calendar secondCalendarDate = new GregorianCalendar(firstCalendarDate.get(Calendar.YEAR),Calendar.JANUARY,1);
        System.out.println("Дата после сдвига на начало года: " + dateFormat.format(secondCalendarDate.getTime()));

        int day = 0;
        Calendar thirdCalendarDate = new GregorianCalendar();
        thirdCalendarDate.setTime(date);

        while(day<10){
            if(thirdCalendarDate.get(Calendar.DAY_OF_WEEK)!=Calendar.SATURDAY &&
                    thirdCalendarDate.get(Calendar.DAY_OF_WEEK)!=Calendar.SUNDAY){
                day++;
                thirdCalendarDate.set(Calendar.DATE,thirdCalendarDate.get(Calendar.DATE)+1);
            }
            else{
                thirdCalendarDate.set(Calendar.DATE,thirdCalendarDate.get(Calendar.DATE)+1);
            }
        }
        System.out.println("Дата после увеличения на 10 рабочих дней: " +
                dateFormat.format(thirdCalendarDate.getTime()));

        System.out.println("Введите вторую дату в формате dd.MM.yyyy:");
        String secondStrDate = in.next();
        Date secondDate;
        try {
            secondDate = dateFormat.parse(secondStrDate);
        }catch (ParseException e){
            System.out.println("Неверный формат даты");
            return;
        }

        Calendar fourthCalendarDate = new GregorianCalendar();
        Calendar fifthCalendarDate = new GregorianCalendar();
        fourthCalendarDate.setTime(secondDate);
        fifthCalendarDate.setTime(date);

        int differenceOfDays = (int)Math.abs(date.getTime()-secondDate.getTime())/(24 * 60 * 60 * 1000);
        int numberOfDayOff = 0;
        for (int i = 0; i < differenceOfDays; i++) {
            if(date.after(secondDate)){
                if (fourthCalendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                        fourthCalendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    numberOfDayOff+=1;
                }
                fourthCalendarDate.set(Calendar.DATE,fourthCalendarDate.get(Calendar.DATE)+1);
            }
            else{
                if (fifthCalendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                        fifthCalendarDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                    numberOfDayOff+=1;
                }
                fifthCalendarDate.set(Calendar.DATE,fifthCalendarDate.get(Calendar.DATE)+1);
            }
        }
        System.out.println("Количество рабочих дней между введенными датами: " + (differenceOfDays-numberOfDayOff));
    }
}
