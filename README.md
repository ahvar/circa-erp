# GRS Manager

## GRSManager GUI
When the GRSManager GUI is constructed it inits LoginPanel, AdministrationPanel, VendorPanel, and ResearchPanel instance variables and displays the LoginPanel. Both the User Directory, Company Directory, and current user instance variables are empty and so the system will only recognize administrator login credentials. 

## AdministrationPanel
The AdministrationPanel has four GUI panels for an research employee directory, vendor employee directory, product catalog, and a company assignment panel. The vendor employee directory panel is shown when AdministratorPanel is constructed

## VendorEmployeeDirectoryPanel
The VendorEmployeeDirectoryPanel has two instance variables for user directory and company directory. When it is constructed these are set to the values currently held by GRSManager. if this is the first time an administrator has logged in, both values will be empty. A user can load a new employee directory or add individual users.

## Adding employees to the vendor directory
If the user clicks the add employee button, the panel tries to add the user to the user directory instance variable. UserDirectory contains a LinkedAbstractLists of users and if the employee is successfully added it returns true. If the company directory does not have an instance of vendor company (i.e. Circassia Pharmaceuticals), then vendor company Circassia is added to the company directory.  