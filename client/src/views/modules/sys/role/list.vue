<template>
    <div class="app-container">
        <div class="filter-container">
            <el-input v-model="listQuery.roleName" placeholder="角色名" style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleFilter" /> 
  
            <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
                搜索
            </el-button>
            <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit"
                @click="handleCreate">
                添加
            </el-button> 
        </div>
  
        <el-table :key="tableKey" v-loading="listLoading" :data="list" border fit highlight-current-row
            style="width: 100%;">
            <el-table-column type="index" width="50" align="center" label="序号">
            </el-table-column>
  
            <el-table-column label="角色名" min-width="150px">
                <template slot-scope="{row}">
                    <span class="link-type" @click="handleUpdate(row)">{{ row.roleName }}</span>
                    <!-- <el-tag>{{ row.type | typeFilter }}</el-tag> -->
                </template>
            </el-table-column>
            <el-table-column label="角色描述">
                <template slot-scope="{row}">
                    <span>{{ row.remark }}</span>
                </template>
            </el-table-column> 
            <el-table-column label="Actions" align="center" width="330" class-name="small-padding fixed-width">
                <template slot-scope="{row,$index}">
                    <el-button type="primary" size="mini" @click="handleUpdate(row)">
                        编辑
                    </el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(row,$index)">
                        删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
  
        <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit"
            @pagination="getList" />
  
        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px"
                style="width: 400px; margin-left:50px;">
                <!-- <el-form-item label="Date" prop="timestamp">
                    <el-date-picker v-model="temp.timestamp" type="datetime" placeholder="Please pick a date" />
                </el-form-item> -->
                <el-form-item label="角色名" prop="roleName">
                    <el-input v-model="temp.roleName" />
                </el-form-item>
                <el-form-item label="描述" prop="remark">
                    <el-input v-model="temp.remark" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="Please input" />
                </el-form-item>
                <el-form-item>
                    <div>
                        <div class="mask" v-show="isShowSelect"></div>
                        <el-popover placement="bottom-start" :width="popoverWidth" trigger="manual" v-model="isShowSelect"
                          @hide="popoverHide">
                          <el-tree class="common-tree" :width="width" ref="tree" :data="treeData" :props="obj" :show-checkbox="multiple"
                            :node-key="obj.id" :check-strictly="checkStrictly" :default-expanded-keys="defaultKeys"
                            :expand-on-click-node="multiple&&expandClickNode" :check-on-click-node="checkClickNode"
                            :highlight-current="true" @check-change="nodeClick" @node-click="nodeClick"></el-tree>
                          <el-select slot="reference" ref="select" :size="size" v-model="returnDataKeys" :multiple="multiple"
                            :clearable="clearable" :collapse-tags="collapseTags" @click.native="selectClick" @remove-tag="removeTag"
                            @clear="clean" class="tree-select">
                            <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value"></el-option>
                          </el-select>
                          <el-row>
                            <el-button v-if="multiple" class="ok" @click="isShowSelect=false" size="mini" type="text">确定</el-button>
                          </el-row>
                        </el-popover>
                      </div>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    取消
                </el-button>
                <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                    提交
                </el-button>
            </div>
        </el-dialog>
  
  
    </div>
  </template>
  
  <script>
    import waves from '@/directive/waves' // waves directive 
  
    import { getRoles, getRole, saveRole, UpdateRole, deleteRole } from '@/api/role'
    import Pagination from '@/components/Pagination' // secondary package based on el-pagination
  
  
    export default {
        name: 'ComplexTable',
        components: { Pagination },
        directives: { waves }, 
        data() {
            return {
                tableKey: 0,
                list: null,
                total: 0,
                listLoading: true,
                listQuery: {
                    page: 1,
                    limit: 10,
                    roleName: ""
                }, 
                temp: {
                    id: "",
                    roleName: '',
                    remark: ''
                },
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: 'Edit',
                    create: 'Create'
                },
                dialogPvVisible: false, 
                rules: { 
                    roleName: [{ required: true, message: 'roleName is required', trigger: 'blur' }]
                },
                downloadLoading: false
            }
        },
        created() {
            this.getList()
        },
        methods: {
            getList() {
                this.listLoading = true
                let parms = 'page=' + this.listQuery.page + '&limit=' + this.listQuery.limit+"&roleName="+this.listQuery.roleName
                getRoles(parms).then(response => { 
                    this.list = response.page.list
                    this.total = response.page.totalCount
  
                    // Just to simulate the time of the request
                    setTimeout(() => {
                        this.listLoading = false
                    }, 1.5 * 1000)
                })
            },
            handleFilter() {
                this.listQuery.page = 1
                this.getList()
            },
            resetTemp() {
                this.temp = {
                    id: "",
                    roleName: '',
                    remark: ''
                }
            },
            handleCreate() {
                this.resetTemp()
                this.dialogStatus = 'create'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            createData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        saveRole(this.temp).then(() => {
                            this.list.unshift(this.temp)
                            this.dialogFormVisible = false
                            this.$notify({
                                title: 'Success',
                                message: 'Created Successfully',
                                type: 'success',
                                duration: 2000
                            })
                        })
                    }
                })
            },
            handleUpdate(row) {
                this.temp = Object.assign({}, row) // copy obj 
                this.dialogStatus = 'update'
                this.dialogFormVisible = true
                this.$nextTick(() => {
                    this.$refs['dataForm'].clearValidate()
                })
            },
            updateData() {
                this.$refs['dataForm'].validate((valid) => {
                    if (valid) {
                        const tempData = Object.assign({}, this.temp)
                        updateRole(tempData).then(() => {
                            const index = this.list.findIndex(v => v.id === this.temp.id)
                            this.list.splice(index, 1, this.temp)
                            this.dialogFormVisible = false
                            this.$notify({
                                title: 'Success',
                                message: 'Update Successfully',
                                type: 'success',
                                duration: 2000
                            })
                        })
                    }
                })
            },
            handleDelete(row, index) {
                let ids = []
                ids.push(row.id)
                deleteRole(ids).then(() => {
                            this.dialogFormVisible = false
                            this.$notify({
                                title: 'Success',
                                message: 'Delete Successfully',
                                type: 'success',
                                duration: 2000
                            })
                            this.handleFilter();
                        }) 
                
            }
        }
    }
  </script>