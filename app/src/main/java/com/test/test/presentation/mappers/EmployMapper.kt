package com.test.test.presentation.mappers

import com.test.test.data.model.Data
import com.test.test.presentation.model.EmployPresentationModel

class EmployMapper
{
    fun fromDomainToPresentation(domainModel: Data) = EmployPresentationModel(
domainModel.id,
        "",
        domainModel.employee_age,
        "","" ,
        "",
        domainModel.employee_name,
        ""
        ,
        domainModel.employee_salary.toDouble()
    )
}