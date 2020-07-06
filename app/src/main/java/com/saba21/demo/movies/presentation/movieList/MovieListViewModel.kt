package com.saba21.demo.movies.presentation.movieList

import com.saba21.demo.domain.useCase.GetFavoriteMoviesUseCase
import com.saba21.demo.domain.useCase.GetPopularMoviesUseCase
import com.saba21.demo.domain.useCase.GetTopRatedMoviesUseCase
import com.saba21.demo.movies.base.viewModel.BaseViewModel
import io.reactivex.Observable
import javax.inject.Inject

class MovieListViewModel
@Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getFavoriteMoviesUseCase: GetFavoriteMoviesUseCase
) : BaseViewModel<MovieListActions, MovieListViewState>() {

    override val initialViewState: MovieListViewState = MovieListViewState.Initial

    val moviePageSize = 20

    override fun onActionReceived(action: MovieListActions): Observable<MovieListViewState> {
        return when (action) {
            is MovieListActions.LoadPopularMoviesPage -> {
                getPopularMoviesUseCase.create(action.page + 1)
                    .map {
                        MovieListViewState.DrawPopularMovies(action.page, it)
                    }
            }
            is MovieListActions.LoadTopRatedMoviesPage -> {
                getTopRatedMoviesUseCase.create(action.page + 1)
                    .map {
                        MovieListViewState.DrawTopRatedMovies(action.page, it)
                    }
            }
            is MovieListActions.LoadFavoriteMoviesPage -> {
                getFavoriteMoviesUseCase.create(Unit)
                    .map {
                        MovieListViewState.DrawFavoriteMovies(it)
                    }
            }
            else -> super.onActionReceived(action)
        }
    }

}