import java.io.File

//***********************************************************************//
// This is a User class.                                                 //
//***********************************************************************//
class User (var realName: String = "",          // We initialize the Object.
            var id: Int = 0,
            var birthdate: Int = 0,
            var userName: String = "",
            var password: String = "")
{
    //***********************************************************************//
    // This function will display the user's information.                    //
    //***********************************************************************//
    fun displayInfo()              // We display the user's info in the screen.
    {
        println("Name: $realName")
        println("id: $id")
        println("Year of birthday $birthdate\n")
    }
}

//***********************************************************************//
// This function will save the user's information to a file and check    //
// if it has been used already.                                          //
//***********************************************************************//
fun saveUserInfo(newUser: User): Boolean
{
    var lines: List<String> = File("texto.txt").readLines()    // Open File with users' data
    for(line in lines)                          // Check every line of the file.
    {
        val parts = line.split(" ")
        if (parts[4] == newUser.userName)           // We check if the username is already been used.
        {                                           // If so, we send the user to the Home Menu.
            println("We are sorry, the username you chose is already being used.")
            println("Try again.")
            return false
        }
    }
    // We save the users' information in the file.
    File("texto.txt").appendText("${newUser.realName} ${newUser.id}" +
            " ${newUser.birthdate} ${newUser.userName} ${newUser.password}\n")
    return true
}

//***********************************************************************//
// This function will create an account for the user.                    //
//***********************************************************************//
fun createAccount(newUser: User)
{
    var canYou: Boolean = false
    do {
        println()
        print("Enter a username for your profile >> ")
        newUser.userName = readln()
        print("Enter a password >> ")
        newUser.password = readln()
        canYou = saveUserInfo(newUser)                  // Save username and password of the user.
    }
    while(!canYou)

    println()
    println("Your profile has been successfully created.")
    println("You will be redirected to our Home Menu")
}

//***********************************************************************//
// This function will ask and check the new user's information.                    //
//***********************************************************************//
fun optionOne() {

    var user = User()                               // Create a new user object.
    var answer = ""
    println()
    println("Perfect!")
    print("Let's create your account. What is your name? >> ")
    user.realName = readln()                                            // We get all the data from the user.
    print("Please enter your ID number >> ")
    user.id = Integer.valueOf(readLine())
    print("Please enter your birthdate year >> ")
    user.birthdate = Integer.valueOf(readLine())
    println()
    println("Make sure your personal information is displayed correctly")
    println()
    user.displayInfo()          // Show data to the user and confirm it is correct.
    println("Do you want to continue?")

    while(answer != "yes")
    {
        print("Enter Yes/No ")
        answer = readLine().toString()
        answer = answer!!.lowercase()

        if (answer == "yes")
            createAccount(user)     // If the information is correct, then we create the account.

        else if (answer == "no")    // If it is not, or the user does not want to create and account,
        {                           // go back to the Home Menu.
            println("You will be redirected to our Home Menu.")
            return
        }
        else {                      // If we get wrong input we go back to the Menu.
            println("Sorry I did not understand that. You will be redirected to our Home Menu.")
            return
        }
    }
}

//***********************************************************************//
// This function will Log in the users to their account.                 //
//***********************************************************************//
fun optionTwo(): Boolean {

    println()
    println("Perfect!")
    println("Let's log in into your account.\n")

    print("Please enter your Username >> ")
    var username = readln()                                             // We get the possible username.
    var lines: List<String> = File("texto.txt").readLines()    // We get all current users' information
    // from a file where it is stored
    for(line in lines) {                               // We read every line from the file.
        val parts = line.split(" ")          // Separate the lines in every space.
        if (parts[4] == username)                     // We check the username colum matches the username entered.
        {
            do {
                print("Please enter your password >> ")
                var password = readln()                     // Get the user's password.
                if (parts[5] == password)                   // If the password matches then the user logs in.
                {
                    println("You have logged in!")
                    println("Stay alert for upcoming features.")
                    return true                            // Finish program.
                }
                else
                {
                    println("Wrong password. Try again")   // If entered password is not correct, ask again.
                }
            }
            while (password != parts[5])
        }
    }

    //If the username does not exist, go back to the Home Menu to create an account.
    println("I am sorry, we could not find any Username with those credentials")
    println("You will be redirected to our Home Menu")
    println("where you can create an account or try to log in with a different username")
    return false
}

//***********************************************************************//
// This function will display and control the Home Page Menu.            //
//***********************************************************************//
fun interact()
{
    var choice = 0
    while (choice != 3)
    {
        println("")
        println("What do you want to do? ")
        println("1.- Sign up")
        println("2.- Log in")
        println("3.- Exit")
        print("Enter the number option >> ")
        choice = Integer.valueOf(readLine())

        if (choice == 1)
        {
            optionOne()         //it is time to create an account
        }
        else if (choice == 2)
        {
            var done =  optionTwo()     // it is time to log in to the account
            if (done)                   // if we logged in we finish the program
            {
                return
            }
        }
        else if (choice == 3)           //Exit the program
        {
            println("EXIT - We hope to see you soon. BYE!")
        }
        else                            //  If the user chooses an invalid option
        {                               //  show message.
            println("I am sorry, please choose an available option")
        }
    }
}

//***********************************************************************//
// This function will display the Home Page Banner.                      //
//***********************************************************************//
fun displayBanner()
{
    println()
    println("                   Welcome to TeAyudo!")
    println("-------------------------------------------------------------")
    println("     ---------                           --------")
    println("    | SIGN UP |                         | LOG IN |")
    println("     ---------                           --------")
    println(" I don't have an account         I already have an account ")
    println("-------------------------------------------------------------")
}

//***********************************************************************//
// This function is the main function. The program starts here.          //
//***********************************************************************//
fun main()
{
    displayBanner()
    interact()
}