<script lang="ts" setup>
import axios from 'axios'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useCounterStore } from '@/stores/counter'
const loginUser = useCounterStore()
const router = useRouter()

interface post {
  board_no: string
  user_no: string
  user_name: string
  title: string
  content: string
  category: string
  view_count: string
  created_date: string
}

const posts = ref<post[]>([]);

const addPosts = ref({
  board_no: '', // 서버에서 구현
  user_no: '', // 로그인 시 user_no 값 가져올 것
  title: '',
  content: '',
  category: '',
  view_count: '0',
  created_date: '', // 서버에서 구현
}  
)
axios.get('/hyeon/boardlist', {
  params: {
    user_no: loginUser.userNo
  }
})
.then(res => {
  if (res.data.boardlist != null) {
      posts.value = res.data.boardlist;
    } else {
      posts.value = [];
    }

})
.catch(err => {

})

function addPost() {
}

function resetPost() {

}

function logout() {

axios.post('/hyeon/logout')
.then(res => {
  if (res.data.responseCode.code == "0000") {
      window.location.href = "/";
    } else {
    }

})
.catch(err => {

})

}
</script>

<template>
  <div class="container mt-5">
    <h2 class="mb-4">📋 게시판</h2>

     환영합니다. {{ loginUser.userName }} 님

    <button v-on:click="logout" class="btn btn-primary">로그아웃</button>


    <!-- 글쓰기 폼 -->
    <form @submit.prevent="addPost" class="mb-4">
      <div class="mb-2">
        <input v-model="addPosts.title" class="form-control" placeholder="제목" required />
      </div>
      <div class="mb-2">
        <input v-model=loginUser.userName class="form-control" placeholder="작성자" required disabled/>
      </div>
      <div class="mb-2">
        <textarea v-model="addPosts.content" class="form-control" rows="3" placeholder="내용" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">작성하기</button>
    </form>

    <!-- 게시글 목록 -->
    <table class="table table-hover">
      <thead class="table-light">
        <tr>
          <th>#</th>
          <th>제목</th>
          <th>작성자</th>
          <th>내용</th>
          <th>카테고리</th>
          <th>조회수</th>
          <th>작성일</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(post, index) in posts" :key="index">
          <td>{{ index + 1 }}</td>
          <td>{{ post.title }}</td>
          <td>{{ post.user_name }}</td>
          <td>{{ post.content }}</td>
          <td>{{ post.category }}</td>
          <td>{{ post.view_count }}</td>
          <td>{{ post.created_date }}</td>
          
        </tr>
      </tbody>
    </table>
  </div>
</template>

<style scoped>
textarea {
  resize: none;
}
</style>