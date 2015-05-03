# ShortSage - a SMS Gateway
ShortSage is a SMS Gateway for generic uses. We give you customable and flexible features for your business.
Currently, we have focused on first build with some features for basic use by customer.

Current main features built on this version of application are:
- Background Service of Modem
- Dashboard Application
- Message folder for inbox and outbox messages
- Phone book of contact person and group
- Send, reply and schedule message(s)
- Gateway modem setup

Limitation on this version are:
- Modem service only can restart by restart application
- Adding new gateway cannot apply until application restarted

Next features will be included future version of application are:
- Import from any format files with user definition columns
- Export data to any format files
- Duplicate detection for contact group, person and message template
- Menu and program setup
- Plugin and extension utility

Current list of tasks to be completed are:
- Inbox and outbox implementation 
   * <del>Listener for all components of inbox and outbox page</del> - <em>partial done at 14 April 2015</em>
   * <del>Load data from database</del> - <em>done but not optimize</em>
- New message implementation
   * <del>Fields validation form</del> - <em>partial done at 14 April 2015</em>
   * <del>Send message both just in time and scheduler</del> - <em>partial done at 14 April 2015</em>
   * <del>Save message into database</del> - <em>partial done at 14 April 2015</em>
   * <del>Message template usage</del> - <em>partial done at 22 April 2015</em>
- Phone Book Implementation
   * <del>Create, update and delete Contact Group</del> - <em>partial done at 24 April 2015</em>
   * <del>Create, update and delete Contact Person</del> - <em>partial done at 24 April 2015</em>
   * Fields Validation form both contact group and contact person
   * Attact Contact Person to Contact Group
   * Load data from database
- Message Template Implementation
   * <del>Create, update and delete message template</del> - <em>partial done at 20 April 2015</em>
   * <del>Fields Validation form</del> - <em>partial done at 20 April 2015</em>
   * <del>Double click row table</del> - <em>partial done at 20 April 2015</em>
   * <del>Search and view listener</del> - <em>partial done at 20 April 2015</em>
   * <del>Load from database</del> - <em>done but not optimize</em>
- <del>Gateway Setup Implementation</del> - <em>partial done at 20 April 2015</em>

<b>Configuration</b>
- Extract javacomm20-win32.zip file
- Copy extracted file following:
  * Copy file comm.jar to [JAVA_HOME]\jre\lib\ext and [JRE_HOME]\jre\lib\ext
  * Copy file javax.comm.properties to [JAVA_HOME]\jre\lib and [JRE_HOME]\jre\lib
  * Copy file win32com.dll to [JAVA_HOME]\jre\bin,[JRE_HOME]\jre\bin and C:\Program Files\WINDOWS\system32