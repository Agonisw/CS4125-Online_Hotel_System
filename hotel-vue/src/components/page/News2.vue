<template>
  <div>
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-lx-cascades"></i>News List
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="container">
      <div class="handle-box">
        <el-input v-model="query.news" placeholder="Please enter the announcement" class="handle-input mr10"></el-input>
        <el-button type="primary" icon="el-icon-search" @click="getNewsInfo" style="margin-left: 20px">search</el-button>
        <el-button type="primary" icon="el-icon-refresh" @click="reset">reset</el-button>
      </div>
      <div class="handle-box">
        <el-button style="margin-bottom: 10px"
                   type="primary"
                   icon="el-icon-plus"
                   class="handle-del mr10"
                   @click="addNewsInfo"
        >add</el-button>
      </div>
      <el-table
              :data="tableData"
              border
              class="table"
              ref="multipleTable"
              header-cell-class-name="table-header"
      >
        <el-table-column type="index" label="index" width="80" align="center"></el-table-column>
        <el-table-column prop="title" label="title"  align="center"></el-table-column>
        <el-table-column prop="news" :show-overflow-tooltip="true" label="news"  align="center"></el-table-column>
        <el-table-column prop="imageUrl" label="image"  align="center">
          <template slot-scope="scope">
            <el-image :src="scope.row.imageUrl" style="width: 50px;height: 50px"></el-image>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="createTime"  align="center"></el-table-column>
        <el-table-column label="operate" width="280" align="center">
          <template slot-scope="scope">
            <el-button
                    type="primary"
                    icon="el-icon-edit"
                    @click="editNewsInfo(scope.row)"
            >edit</el-button>
            <el-button
                    type="primary"
                    icon="el-icon-edit"
                    @click="delNewsInfo(scope.row.id)"
            >delete</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
                background
                layout="total, prev, pager, next"
                :current-page="query.pageNum"
                :page-size="query.pageSize"
                :total="pageTotal"
                @current-change="handlePageChange"
        ></el-pagination>
      </div>
    </div>
    <!-- 添加弹出框 -->
    <el-dialog title="operate" :visible.sync="addVisible" width="30%">
      <el-form ref="form" :model="form" label-width="70px">
        <el-form-item label="title" label-width="100px" prop="roleName">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="news" label-width="100px" prop="roleName">
          <el-input v-model="form.news"></el-input>
        </el-form-item>
        <el-form-item label="image" label-width="100px" prop="roleName">
          <single-upload v-model="form.imageUrl"></single-upload>
        </el-form-item>
      </el-form>
        <span slot="footer" class="dialog-footer">
                <el-button @click="addVisible = false">cancel</el-button>
                <el-button type="primary" @click="saveAndUpdateNewsInfo">confirm</el-button>
            </span>
    </el-dialog>

  </div>
</template>

<script>


  import { getNewsList, saveAndUpdateNewsInfo, delNewsInfo, updateNewsInfo, saveNewsInfo } from '../../api/cake';
  import SingleUpload from '../common/singleUpload';

  export default {
    name: 'News',
    components: { SingleUpload },
    data() {
      return {
        query: {
          pageNum:1,
          pageSize:10,
          news:null
        },
        tableData: [],
        option:[],
        pageTotal: 0,
        addVisible:false,
        form:{},
        jobList:[],

      };
    },
    created() {
      this.getData();
    },


    computed:{
      userType(){
        return sessionStorage.getItem("userType");
      }
    },

    methods: {

      /**
       * 获取表格中的数据
       */
      getData() {
        this.query.type = '1';
        getNewsList(this.query).then(res => {
          if (res.code === 1) {
            this.tableData = res.data.list;
            this.pageTotal = res.data.total;
          }else {
            console.log('fail');
          }
        });
      },

      addNewsInfo(){
        this.addVisible = true;
        this.form = {};
      },

      saveAndUpdateNewsInfo(){
        this.form.type = '1';
        this.form.userId = sessionStorage.getItem('userId');
        if(this.form.id == null){
          saveNewsInfo(this.form).then(res =>{
            if (res.code === 1){
              this.addVisible = false;
              this.$message.success('operate successfully');
              this.getData();
            }else {
              this.$message.error('operate fail');
            }
          })
        }else {
          updateNewsInfo(this.form).then(res =>{
            if (res.code === 1){
              this.addVisible = false;
              this.$message.success('operate successfully');
              this.getData();
            }else {
              this.$message.error('operate fail');
            }
          })
        }

      },

      editNewsInfo(row){
        this.form.id = row.id;
        this.form = JSON.parse(JSON.stringify(row));
        this.addVisible = true;
      },

      delNewsInfo(id){
        this.$confirm('Are you sure you want to delete the selected one？', 'hint', {
          type: 'warning'
        }).then(action =>{
          if (action === 'confirm'){
            delNewsInfo(id).then(res =>{
              console.log('==');
              console.log(res);
              if (res.code === 1){
                this.$message.success('successfully delete');
                this.getData();
              }else{
                this.$message.error('fail to delete');
              }
            })
          }
        })
      },

      getNewsInfo(){
        this.getData();
      },

      handlePageChange(val) {
        this.$set(this.query, 'pageNum', val);
        this.getData();
      },

      reset(){
        this.query.news = null;
        this.getData();
      },


    }
  };
</script>

<style scoped>

  .image{
    height: 30px;
  }
  .handle-box {
    margin-top: 20px;
    margin-bottom: 10px;
  }
  /deep/ .el-upload--text{
    width: 98px !important;
    height: 34px !important;
    display: inline-grid;
    float: left;
    margin-right: 10px;
    border: 1px solid #DCDFE6;
    border-radius: 3px;
  }
  .el-upload--text .inline-block {
    display: inline-block;
    border: 0px dashed #d9d9d9
  }

  .handle-input {
    width: 300px;
    display: inline-block;
  }
  .table {
    width: 100%;
    font-size: 14px;
  }
</style>
