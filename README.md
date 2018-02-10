# Android
## The overall project
### What is it?

#### Startouch: like instagram but for reviews
This is an Android app for sharing your opinions on stuff with friends. Opinions are structured as "reviews" with a subject, a rating 
out of 5 stars, and some hashtags for categorisation. Users can add optional comments, images, locations, criteria and facts but 
only a subject, rating and at least one tag are necessary. These micro-opinions are published to your feed and will appear on the 
feed of friends who follow you. They can also be shared on Facebook and Twitter.

### Structure
There are three modules that make up the project:

#### [Startouch](https://github.com/chdryra/Startouch)
The core app. Where the magic happens.

#### [CoreLibrary](https://github.com/chdryra/CoreLibrary)
Contains all classes that are not directly related to the Startouch app and that could be used in other apps.

#### [TestUtils](https://github.com/chdryra/TestUtils)
Classes to aid testing, like data mockers and random data generators. Testing not currently up-to-date (I know I'm a bad person...).
