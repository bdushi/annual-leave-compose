package com.example.compose.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.compose.data.source.LeaveRepository
import com.example.compose.model.Leave

class LeavePageSource constructor(
        private val leaveRepository: LeaveRepository
        ) : PagingSource<Int, Leave>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Leave> {
        return try {
            val nextPage = params.key ?: 0
            val response = leaveRepository.leave("", nextPage, params.loadSize)
            if(response.isSuccessful) {
                if(response.body() != null) {
                    LoadResult.Page(
                            data = response.body()!!.leave,
                            prevKey = if (nextPage == 0) nextPage else nextPage - 1,
                            nextKey = if (nextPage >= response.body()!!.numberOfElements) nextPage else response.body()!!.number + 1
                    )
                } else {
                    LoadResult.Error(Exception("No Data"))
                }
            } else {
                LoadResult.Error(Exception(response.message()))
            }
        } catch (ex: Exception) {
            LoadResult.Error(ex)
        }
    }

    override val keyReuseSupported: Boolean
        get() = true

    override fun getRefreshKey(state: PagingState<Int, Leave>): Int? {
        return state.anchorPosition
    }
}