package example.tnegishi.mvvmsampleapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import example.tnegishi.mvvmsampleapp.R
import example.tnegishi.mvvmsampleapp.model.Project
import example.tnegishi.mvvmsampleapp.repository.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProjectRepository.instance

    var projectListLiveData: MutableLiveData<List<Project>> = MutableLiveData()

    init {
        loadProjectList()
    }

    private fun loadProjectList() {
        //viewModelScope->ViewModel.onCleared() のタイミングでキャンセルされる CoroutineScope
        viewModelScope.launch {
            try {
                val request = repository.getProjectList(getApplication<Application>().getString(R.string.github_user_name))
                if (request.isSuccessful) {
                    projectListLiveData.postValue(request.body())
                }
            } catch (e: Exception) {
                e.stackTrace
            }
        }
    }
}