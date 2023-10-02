**Object-Oriented Development**


**Tasks:** Implement an agenda (Agenda class) based on STL containers (std::map) and the Date class to manage appointments. The following operations should be performed:

- Add an appointment by specifying the date (Date), time (std::string in the format hh:mm), and appointment details (std::string).
- List all appointments in ascending order of dates/times.
- Display an appointment entered in the list.
- Modify the date of an appointment (for example, reschedule the appointment by 3 days, and the new date should be automatically calculated by the Date class).
- Delete an appointment.
- Save the agenda to a file.
- Load the agenda from a file.

**Bonus:** The search functionality is :
- Search appointments by date (2 points).
- Search appointments by keyword (2 points).

**Structure:** Below is a proposed structure for the project, but other structures and classes are still allowed (with a report detailing the design).
- The STL std::map is used to manage appointments, specifically the std::map<Date, std::map<std::string, std::string>> structure to organize appointments by date - time - appointment details.
- The Agenda class manages the above std::map.
- The Date class manages dates (mandatory).