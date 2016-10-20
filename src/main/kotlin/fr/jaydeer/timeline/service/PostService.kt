package fr.jaydeer.timeline.service

import fr.jaydeer.common.service.EntityService
import fr.jaydeer.timeline.entity.Post
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Service

@Service
class PostService(repository: PagingAndSortingRepository<Post, String>): EntityService<Post, String>(repository) {
}