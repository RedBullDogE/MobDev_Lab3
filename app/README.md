# Mobile development

## LW3

App consists of MainActivity with ViewPagerAdapter which displays two fragments (movie list - MovieListFragment and blank - AnotherOneFragment).

1. Tabs was implemented with TabLayout and ViewPagerAdapter
2. Movie class was created for describing movie entity.
3. All movies are stored in movieList: ArrayList<Movie> in CustomMovieListAdapter
4. The list of movies as view are implemented in CustomMovieListAdapter. This adapter is responsible for displaying movie_item views

The main problem was to set up adapters to work with tabs (viewpager) and fragments and understand the logic of their work

### Results
[Video with results for LW3](https://drive.google.com/file/d/1dR9lF5dQ98jW2x74JJYd5rtF1wszq5l1/view?usp=sharing)


## LW4

App consists of MainActivity with ViewPagerAdapter which displays two fragments (movie list - MovieListFragment and blank - AnotherOneFragment).

1. MovieDetailsActivity was created for movie details window.
2. Was added new extended class for movies - MovieDetails.
3. MovieAddActivity - activity for adding new movies.
4. SearchView for searching movies by title was added on the top bar.
5. Deleting movies was implemented with long click on list item.

### Results

[Video with results for LW4](https://drive.google.com/file/d/1AUkPlVz5T7H5wljTe38p7n_m8pIzTQXd/view?usp=sharing)

## LW5

Was added new fragment displaying the gallery. 

1. User can push own pictures by tapping the add button.
2. The Gallery has a predefined structure (1 variant).
3. Pictures are placed completely filling their space without losing proportions, although they can be cropped.

[Videw with the results for LW5](https://drive.google.com/file/d/1zwopBk-uS0djdkQmlX4ENFrJTto27YMb/view?usp=sharing)
