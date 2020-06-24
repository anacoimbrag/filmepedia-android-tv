# Filmepedia
An **Android TV** project that uses The Movie Database to list movies on TV. Just another educational project for learning purposes.

## Setup

Since this project consumes [The Movie DB API](https://www.themoviedb.org/) you need to create an 
account (if you don't already have one) and get your API key. Checkout the [docs](https://developers.themoviedb.org/3/getting-started/introduction) for this step.

Once you have your API key, you should add it to your environment variables. To do so you need to add this line:
```shell script
export TMDB_KEY=<your_api_key>
```  
inside your `~/.bashrc` or `~/.zshrc` if you use MacOS, or inside `/etc/profile` if you use Linux.

if you use Windows you can configure this in the Control Panel of the operating system or you can run on terminal: 
```shell script
setx -m TMDB_KEY <your_api_key>
```

After this step restart your terminal and if Android Studio is running you need to restart it too so 
the new variables are indexed and identified.

## Run

To run this app you need to setup an Android TV device (virtual or physical). This is almost the 
same configurations as smartphone apps, using ADB. Checkout the [docs](https://developer.android.com/training/tv/start/start?hl=en#run).

## Learn 

If you want to know more about developing for Android TV checkout the development [docs](https://developer.android.com/training/tv/start?hl=en) 