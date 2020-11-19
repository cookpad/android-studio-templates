# Cookpad Android Studio Templates - IntellJ plugin
Provides a set of templates for scaffolding architectural repetitive tasks.

## Installation
Install the [`Cookpad Android Studio Templates`](https://plugins.jetbrains.com/plugin/15455-cookpad-android-studio-templates) IntellJ plugin in Android Studio. [Instructions](https://stackoverflow.com/a/30617737/1525990) for how to install any plugin.

<img src="/images/install.png" class="center" width="50%">

## Usage

## Screen template
This template adds all the required components to create a new feature based on the config provided by the user.

* Click with right button on the module that you want to create the new feature.
* Select `New -> Other -> Cookpad Feature Screen`. 
<img src="/images/1_screen.png" class="center" width="50%">

* Write the name of the new feature.
* In the drop-down list "Add pagination support", you can choose between "none" and "Standard pagination".
* In the drop-down list "Add delegation support", you can choose between "none", "a viewModel delegate" and "a viewModel delegate and a view delegate".
<img src="/images/2_screen.png" class="center" width="50%">

## Module template
This template add a new Android Gradle module with all the basic setup that we usually require when creating a a new module.
* Click with right button on any existing Gradle module (Android Studio does not allow to use template outside a preexisting Android module).
* Select `New -> Application -> Cookpad Module`.
<img src="/images/1_module.png" class="center" width="50%">

* Write the name of the new module.
<img src="/images/2_module.png" class="center" width="50%">

## Cookpad codebase coupling
* `Screen template` relies in two custom components declared inside the non-published Cookpad code base: SingleLiveData, used for emitting events with LiveData only one time -no caching, and [FragmentViewBindingDelegate](https://medium.com/@Zhuinden/simple-one-liner-viewbinding-in-fragments-and-activities-with-kotlin-961430c6c07c) to bind the view and clear the view binding once the fragment view is destroyed. You will need to replace that parts by an analogous implementation.
* The injection of dependencies is done via [Koin](https://github.com/InsertKoinIO/koin).

