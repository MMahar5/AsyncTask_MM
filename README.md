# AsyncTask_MM

The assignment was to run an example application to demonstrate Android threading in the background. (AsyncTask)


When starting the application, there is a simple layout with a Button, a couple of TextViews (one can't be seen in pic, used for progress update), and an EditText. The user enters a value for the number of seconds and an AsyncTask process is started to sleep for that amount of seconds.
![Capture1](https://github.com/MMahar5/AsyncTask_MM/assets/97249776/a20379e3-3ad8-40ca-80f6-99235f81d85f)


I added a toast message to show if the user clicks the button without entering a number value. 
![Capture2](https://github.com/MMahar5/AsyncTask_MM/assets/97249776/452c34ff-6109-4701-8627-7e457d29d800)


After clicking the button, the onPreExecute() method runs first and shows a popup for the "Progress Dialog" and the number of seconds it is sleeping for. As we can see, I entered 10 seconds. Then the doInBackground() method runs in the background to process the sleep task and send any progress updates via the publishProgress() and onProgressUpdate() methods. In this case, we are just showing "Sleeping...".
![Capture3](https://github.com/MMahar5/AsyncTask_MM/assets/97249776/672e258b-2333-4ce3-ad79-042e89ad7684)


When finished, we see our progress message has changed to "Slept for 10 seconds". This is done once the doInBackground() method is complete and returns a value to onPostExecute() to change the main UI thread. 
![Capture4](https://github.com/MMahar5/AsyncTask_MM/assets/97249776/8043f049-6a9d-43b3-9173-0f08e730bc9f)


Here is an overview of the code for the 4 AsyncTask methods. onPreExecute() runs on the UI thread and sets up the progress dialog, doInBackground() runs on the background thread to process the task and uses Thread.Sleep() to initiate the number of seconds to sleep for, onProgressUpdate() runs on main UI thread to update the progress message from publishProgress() in doInBackground(), and onPostExecute() updates the UI thread once it receives the return response from doInBackground().
![Capture5](https://github.com/MMahar5/AsyncTask_MM/assets/97249776/0bb7fa4d-00d9-450b-99a3-5b9c91b3a431)



Source of example tutorial:
https://www.digitalocean.com/community/tutorials/android-asynctask-example-tutorial
 
