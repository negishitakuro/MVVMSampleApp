package example.tnegishi.mvvmsampleapp.repository

import example.tnegishi.mvvmsampleapp.model.Project
import example.tnegishi.mvvmsampleapp.service.GithubService
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectRepository {

    private val retrofit = Retrofit.Builder()
        .baseUrl("HTTPS_API_GITHUB_URL")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val githubService: GithubService = retrofit.create(GithubService::class.java)

    suspend fun getProjectList(userId: String): Response<List<Project>> =
        githubService.getProjectList(userId)

    suspend fun getProjectDetails(userId: String, projectName: String): Response<Project> =
        githubService.getProjectDetails(userId, projectName)

    companion object Factory {
        val instance: ProjectRepository
            @Synchronized get() {
                return ProjectRepository()
            }
    }
}