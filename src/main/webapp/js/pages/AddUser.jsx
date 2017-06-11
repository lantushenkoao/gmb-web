import React, {Component} from 'react';
import Header from '../components/Header.jsx';
import UserDetails from "../components/UserDetails.jsx";

module.exports = class AddUser extends Component{

    handleSubmit(id, login, password, fullName){
        let self = this;
        $.ajax({
            type:'POST',
            url:'/api/user/add',
            data: {
                id: id,
                login: login,
                password: password,
                fullName: fullName,
            }
        }).done(function(){
            self.props.history.push('/users/list');
        }).fail(function(xhr){
            alert('Internal error');
        });
    }
    render() {
        return (
            <div>
                <Header />
                <h2>Add user</h2>
                <div>
                    <UserDetails
                        submitButtonText="Create"
                        loadDataUrl={null}
                        isPasswordMandatory={true}
                        handleSubmitCallback={this.handleSubmit}/>
                </div>
            </div>
        );
    }
};