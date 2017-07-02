import React, {Component} from 'react';

import Multiselect from 'react-widgets/lib/Multiselect';
import DropdownList from 'react-widgets/lib/DropdownList';
import DateTimePicker from 'react-widgets/lib/DateTimePicker';
import NumberPicker from 'react-widgets/lib/NumberPicker';

//react widgets http://jquense.github.io/react-widgets/docs/#/?_k=h010sg
class ParametersSelector extends Component {
    constructor(props) {
        super(props)
    }

    render(){
        return <div>
            <div className="row">
                <div className="col-lg-4">
                    Станции
                    <DropdownList
                        data={this.props.stations}
                        valueField = "id"
                        textField = "name"
                        filter = "contains"
                        caseSensitive = {false}
                        value={this.props.selectedStations}
                        onChange={(value)=>this.props.onParameterChanged('selectedStations', value)}
                    />
                </div>
                <div className="col-lg-8">
                    Типы данных
                    <Multiselect
                        data={this.props.fields}
                        valueField = "id"
                        textField = "name"
                        filter = "contains"
                        caseSensitive = {false}
                        value={this.props.selectedFields}
                        onChange={(value)=>this.props.onParameterChanged('selectedFields', value)}
                    />
                </div>
            </div>
            <div className="row">
                <div className="col-lg-2">
                    Начальная дата
                    <DateTimePicker time={false}
                                    value={this.props.startDate}
                                    onChange={(value)=>this.props.onParameterChanged('startDate', value)}/>
                </div>
                <div className="col-lg-2">
                    Конечная дата
                    <DateTimePicker time={false} defaultValue={null}
                                    value={this.props.endDate}
                                    onChange={(value)=>this.props.onParameterChanged('endDate', value)}/>
                </div>
                <div className="col-lg-2">
                    Количество точек
                    <NumberPicker value={this.props.bucketsCount}
                                  min={0}
                                  onChange={(value)=>this.props.onParameterChanged('bucketsCount', value)}/>
                </div>
            </div>
        </div>
    }
}

ParametersSelector.propTypes = {
    onParameterChanged : React.PropTypes.func.isRequired,
    stations: React.PropTypes.array.isRequired,
    selectedStations: React.PropTypes.object,
    fields: React.PropTypes.array.isRequired,
    selectedFields: React.PropTypes.array,
    startDate: React.PropTypes.object,
    endDate: React.PropTypes.object,
    bucketsCount: React.PropTypes.number
};

module.exports = ParametersSelector;