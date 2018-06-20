# GRS Manager
[Contract research organizations (CRO)](https://en.wikipedia.org/wiki/Contract_research_organization) provide a variety of clinical research services to pharmaceutical companies. CROs give healthcare companies the option of outsourcing the staff and functions required to conduct research on their product. 

GRSManager is an order entry and tracking program that automates the work-flow associated with a CRO placing orders for a vendor's products. Since CROs frequently manage multiple clinical trials, each with several associated research sites, the program organizes this information into a data model that allows the CRO's user to order products for specific studies and locations. Multiple user authentication into the program enables a vendor's users to then login, process, and modify the status of a particular order. A third type of user, the system administrator, must first load product and order history data so it is available for CRO users placing orders. 

## Getting Started

#### Using Eclipse in Windows

###### Download GRSManager or clone the repo to your local machine. Import GRSManager into your workbench:
1. Click the File menu and select *import*
2. Select the *File System* folder and click next 
3. Browse file system to select the local repo and select finish

###### Running the program
1. Open source file tree and find/select the com.circa.mrv.grs_manager.ui package.
2. R-click GRSManagerGUI.java and select *Run as Java program*
3. The following login screen should be displayed

![Login](project_docs/login.png)


## Using GRSManager


GRSManager implements an MVC design. A single instance of the controlling class, Manager.java, receives and processes all GUI requests. For example, when a CRO user is placing an order and he/she selects a particThe GUI requests order and product data via the controller class, Manager.java, A a controller which where system users from a CRO can create orders that are viewed and monitored by their vendor., adding specific products, and tracking the status of each request. It is tailored to the specific needs of a contract research organization where users can select research studies and locations. Allows for multiple user authentication so a customer's users can place orders and vendor users can process. An administrator loads order history and product data 

##### GRSManager GUI
When the GRSManager GUI is constructed it inits LoginPanel, AdministrationPanel, VendorPanel, and ResearchPanel instance variables and displays the LoginPanel. Both the User Directory, Company Directory, and current user instance variables are empty and so the system will only recognize administrator login credentials. 

##### AdministrationPanel
The AdministrationPanel has four GUI panels for an research employee directory, vendor employee directory, product catalog, and a company assignment panel. The vendor employee directory panel is shown when AdministratorPanel is constructed

##### VendorEmployeeDirectoryPanel
The VendorEmployeeDirectoryPanel has two instance variables for user directory and company directory. When it is constructed these are set to the values currently held by GRSManager. if this is the first time an administrator has logged in, both values will be empty. A user can load a new employee directory or add individual users.

##### Adding employees to the vendor directory
If the user clicks the add employee button, the panel tries to add the user to the user directory instance variable. UserDirectory contains a LinkedAbstractLists of users and if the employee is successfully added it returns true. If the company directory does not have an instance of vendor company (i.e. Circassia Pharmaceuticals), then vendor company Circassia is added to the company directory.  

### Development
GRSManager is developed using JRE 1.8.0 in Eclipse.

### Todos

 - VendorCustomerOrderSchedulePanel
 - ResearchCompanyOrderEntryPanel