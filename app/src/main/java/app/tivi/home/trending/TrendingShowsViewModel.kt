/*
 * Copyright 2017 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.home.trending

import app.tivi.data.resultentities.TrendingEntryWithShow
import app.tivi.interactors.UpdateTrendingShows
import app.tivi.interactors.UpdateTrendingShows.Page.NEXT_PAGE
import app.tivi.interactors.UpdateTrendingShows.Page.REFRESH
import app.tivi.interactors.execute
import app.tivi.tmdb.TmdbManager
import app.tivi.util.AppCoroutineDispatchers
import app.tivi.util.AppRxSchedulers
import app.tivi.util.EntryViewModel
import app.tivi.util.Logger
import javax.inject.Inject

class TrendingShowsViewModel @Inject constructor(
    schedulers: AppRxSchedulers,
    dispatchers: AppCoroutineDispatchers,
    private val interactor: UpdateTrendingShows,
    tmdbManager: TmdbManager,
    logger: Logger
) : EntryViewModel<TrendingEntryWithShow>(
        schedulers,
        dispatchers,
        interactor.dataSourceFactory(),
        tmdbManager,
        logger
) {
    override suspend fun callLoadMore() = interactor.execute(UpdateTrendingShows.ExecuteParams(NEXT_PAGE))

    override suspend fun callRefresh() = interactor.execute(UpdateTrendingShows.ExecuteParams(REFRESH))
}
