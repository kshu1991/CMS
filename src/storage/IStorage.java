package storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import cms.model.*;

public class IStorage {
	public boolean load(Model model) {
		JSONParser parser = new JSONParser();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
		try {
			JSONObject CMSObj = (JSONObject) parser.parse(new FileReader("calendarsList.json"));
			
			JSONArray calendarsList = (JSONArray) CMSObj.get("calendars");
			for (int i = 0; i < calendarsList.size(); i++) {
				JSONObject calendarObj = (JSONObject) calendarsList.get(i);
				CMSCalendar calendar = new CMSCalendar();
				model.addCalendar(calendar);
				calendar.setName((String) calendarObj.get("name"));
				calendar.setDuration(((Long) calendarObj.get("duration")).intValue());
				calendar.setStartTime(LocalTime.parse((String) calendarObj.get("start"), dtf));
				calendar.setEndTime(LocalTime.parse((String) calendarObj.get("end"), dtf));
				JSONArray monthsList = (JSONArray) calendarObj.get("months");
				for (int j = 0; j < monthsList.size(); j++) {
					JSONObject monthObj = (JSONObject) monthsList.get(j);
					CMSMonth month = new CMSMonth(LocalDate.parse((String) monthObj.get("cmsmonth"), formatter));
					calendar.addCMSMonths(month);
					JSONArray datesList = (JSONArray) monthObj.get("dates");
					for (int k = 0; k < datesList.size(); k++) {
						JSONObject dateObj = (JSONObject) datesList.get(k);
						CMSDate date = new CMSDate(LocalDate.parse((String) dateObj.get("date"), formatter));
						month.addCMSDates(date);
						JSONArray timeslotsList = (JSONArray) dateObj.get("timeslots");
						for (int l = 0; l < timeslotsList.size(); l++) {
							JSONObject timeslotObj = (JSONObject) timeslotsList.get(l);
							LocalDate localdate = LocalDate.parse((String) timeslotObj.get("date"), formatter);
							LocalTime start_time = LocalTime.parse((String) timeslotObj.get("start time"), dtf);
							int duration = ((Long) timeslotObj.get("duration")).intValue();
							int status = ((Long) timeslotObj.get("status")).intValue();
							String person = (String) timeslotObj.get("person");
							String location = (String) timeslotObj.get("location");
							Timeslot timeslot = new Timeslot(localdate, start_time, duration, status, person, location);
							date.addTimeslots(timeslot);
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean store(Model model) {
		JSONObject CMSObj = new JSONObject();
		JSONArray calendarsList = new JSONArray();
		
		for (CMSCalendar calendar: model.getCalendars()) {
			JSONObject calendarObj = new JSONObject();
			calendarObj.put("name", calendar.getName());
			calendarObj.put("duration", calendar.getDuration());
			calendarObj.put("start", calendar.getStartTime().toString());
			calendarObj.put("end", calendar.getEndTime().toString());
			JSONArray monthsList = new JSONArray();
			for (CMSMonth month: calendar.getCMSMonths()) {
				JSONObject monthObj = new JSONObject();
				monthObj.put("cmsmonth", month.getCMSMonth().toString());
				JSONArray datesList = new JSONArray();
				for (CMSDate date: month.getCMSDates()) {
					JSONObject dateObj = new JSONObject();
					dateObj.put("date", date.getDate().toString());
					JSONArray timeslotsList = new JSONArray();
					for (Timeslot timeslot: date.getTimeslots()) {
						JSONObject timeslotObj = new JSONObject();
						timeslotObj.put("date", timeslot.getDate().toString());
						timeslotObj.put("start time", timeslot.getStartTime().toString());
						timeslotObj.put("duration", timeslot.getDuration());
						timeslotObj.put("person", timeslot.getPerson());
						timeslotObj.put("location", timeslot.getLocation());
						timeslotObj.put("status", timeslot.getStatus());
						timeslotsList.add(timeslotObj);
					}
					dateObj.put("timeslots", timeslotsList);
					datesList.add(dateObj);
				}
				monthObj.put("dates", datesList);
				monthsList.add(monthObj);
			}
			calendarObj.put("months", monthsList);
			calendarsList.add(calendarObj);
		}
		CMSObj.put("calendars", calendarsList);
		
		try (FileWriter file = new FileWriter("calendarsList.json")) {
            file.write(CMSObj.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return true;
	}
}
