<template>
  <div id="app">
    <el-row>
      <el-col :span="6">
        <el-form :model="form" :rules="formRules" ref="ruleForm" label-width="120px" >
          <el-form-item label="Personal Code:" prop="personalCode">
            <el-input v-model="form.personalCode"></el-input>
          </el-form-item>
          <el-form-item label="Loan Amount:" prop="loanAmount">
            <el-input-number v-model="form.loanAmount" controls-position="right" :min="2000" :max="10000"></el-input-number>
          </el-form-item>
          <el-form-item label="Loan Period (months):" prop="loanPeriod">
            <el-input-number v-model="form.loanPeriod" controls-position="right" :min="12" :max="60"></el-input-number>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="submitForm('ruleForm')">Submit</el-button>
      </el-col>
    </el-row>
    <el-tag v-if="form.decision" :type="form.decision === 'POSITIVE' ? 'success' : 'danger'" class="credit-tag">{{form.decision }}</el-tag>
    <el-tag v-if="form.approvedAmount" class="credit-tag">Approved Amount: {{ form.approvedAmount }}</el-tag>
  </div>
</template>

<script>
import { getDecision } from "@/api/credit"
export default {
  name: 'App',
  data() {
    return {
      form: {
        personalCode: '',
        loanAmount: 2000,
        loanPeriod: 12,
        decision: null,
        approvedAmount: null
      },
      formRules: {
        personalCode: [
          { required: true, message: 'Please input Personal code', trigger: 'blur' },
          { min: 1, message: 'Length can\'t be empty', trigger: 'blur' }
        ],
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          getDecision(this.form).then((res) => {
            this.form.decision = res.data.decision;
            this.form.approvedAmount = res.data.approvedAmount;
          }).catch(() => {
            this.$message.error('Error')
          })
        } else {
          this.$message.error('Fill all the fields')
          return false;
        }
      });
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.credit-tag {
  margin: 0 10px;
}
</style>
