<template>
    <div class="app-container">
        <div class="filter-container">
            <el-input v-model="listQuery.username" placeholder="用户名" style="width: 200px;" class="filter-item"
                @keyup.enter.native="handleFilter" />
            <el-select v-model="listQuery.status" placeholder="状态" clearable style="width: 90px" class="filter-item">
                <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
            </el-select>

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

            <el-table-column label="用户名" min-width="150px">
                <template slot-scope="{row}">
                    <span class="link-type" @click="handleUpdate(row)">{{ row.username }}</span>
                    <!-- <el-tag>{{ row.type | typeFilter }}</el-tag> -->
                </template>
            </el-table-column>
            <el-table-column label="昵称" width="110px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.nickname }}</span>
                </template>
            </el-table-column>
            <el-table-column label="创建时间" width="180px" align="center">
                <template slot-scope="{row}">
                    <span>{{ row.createTime | parseTime('{y}-{m}-{d} {h}:{i}:{s}') }}</span>
                </template>
            </el-table-column>
            <el-table-column label="用户状态" class-name="status-col" width="100" align="center">
                <template slot-scope="{row}">
                    <el-tag v-if='row.status' :type="row.status | statusFilter">
                        正常
                    </el-tag>
                    <el-tag v-else :type="row.status | statusFilter">
                        禁用
                    </el-tag>
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
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="temp.username" />
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="temp.nickname" />
                </el-form-item>
                <el-form-item label="Status">
                    <el-select v-model="temp.status" class="filter-item" placeholder="Please select">
                        <el-option v-for="item in statusOptions" :key="item" :label="item" :value="item" />
                    </el-select>
                </el-form-item>
                <el-form-item v-if="dialogStatus=='create'" label="密码">
                    <el-input v-model="temp.password" type="password" />
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
    import { parseTime } from '@/utils'

    import { getUsers, getUser, saveUser, UpdateUser, deleteUser } from '@/api/user'
    import Pagination from '@/components/Pagination' // secondary package based on el-pagination


    export default {
        name: 'ComplexTable',
        components: { Pagination },
        directives: { waves },
        filters: {
            statusFilter(status) {
                const statusMap = {
                    1: 'success',
                    0: 'danger'
                }
                return statusMap[status]
            }
        },
        data() {
            return {
                tableKey: 0,
                list: null,
                total: 0,
                listLoading: true,
                listQuery: {
                    page: 1,
                    limit: 10,
                    username: "",
                    status: "",
                },
                statusOptions: [0, 1],
                temp: {
                    id: "",
                    username: '',
                    status: '1',
                    password: ''
                },
                dialogFormVisible: false,
                dialogStatus: '',
                textMap: {
                    update: 'Edit',
                    create: 'Create'
                },
                dialogPvVisible: false,
                rules: {
                    status: [{ required: true, message: 'type is required', trigger: 'change' }],
                    timestamp: [{ type: 'date', required: true, message: 'timestamp is required', trigger: 'change' }],
                    username: [{ required: true, message: 'username is required', trigger: 'blur' }],
                    password: [{ required: true, message: 'password is required', trigger: 'blur' }]
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
                let parms = 'page=' + this.listQuery.page + '&limit=' + this.listQuery.limit + "&username=" + this.listQuery.username + "&status=" + this.listQuery.status
                getUsers(parms).then(response => {
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
                    username: '',
                    status: '1',
                    password: ''
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
                        saveUser(this.temp).then(() => {
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
                        updateUser(tempData).then(() => {
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
                deleteUser(ids).then(() => {
                    this.dialogFormVisible = false
                    this.$notify({
                        title: 'Success',
                        message: 'Delete Successfully',
                        type: 'success',
                        duration: 2000
                    })
                    this.handleFilter();
                })

            },
            formatJson(filterVal) {
                return this.list.map(v => filterVal.map(j => {
                    if (j === 'timestamp') {
                        return parseTime(v[j])
                    } else {
                        return v[j]
                    }
                }))
            }
        }
    }
</script>